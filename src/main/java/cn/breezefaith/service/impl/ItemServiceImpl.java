package cn.breezefaith.service.impl;

import cn.breezefaith.constant.Cons;
import cn.breezefaith.dao.IItemDao;
import cn.breezefaith.entity.Item;
import cn.breezefaith.service.IItemService;
import cn.breezefaith.util.ImageUtil;
import cn.breezefaith.util.JSONUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements IItemService {
    @Resource(name = "itemDao")
    private IItemDao itemDao;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public List<Item> findAll() {
        return itemDao.findAll();
    }

    @Override
    public List<Item> findAllWithRedis() {
        Jedis jedis=jedisPool.getResource();
        List<Item> items=itemDao.findAll();
        for (Item item : items) {
            jedis.set("item"+item.getIid().toString(), JSONUtil.parseJSONString(item));
            jedis.set("itemImage"+item.getIid().toString(), ImageUtil.getBinaryString(item.getItemImage()));
        }
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return items;
    }

    @Override
    public List<Item> findAllInRedis(){
        Jedis jedis=jedisPool.getResource();
        List<Item> items=(List<Item>)JSONUtil.decode(jedis.get("items"), new TypeReference<List<Item>>() {});
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return items;

    }

    @Override
    public byte[] getImage(String key) {
        Jedis jedis=jedisPool.getResource();
        byte[] imageStream=ImageUtil.getBinaryBytes(jedis.get(key));
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return imageStream;
    }

    @Override
    public Item findById(String itemId) {
        return itemDao.findById(itemId);
    }

    @Override
    public String getItemList() {
        Jedis jedis=jedisPool.getResource();
        String items=jedis.get("items");
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return items;
    }

    @Override
    public boolean addToCart(String token, String itemId) {
        Jedis jedis=jedisPool.getResource();
        try{
            if(jedis.get("cart"+token)==null){
                jedis.set("cart"+token,JSONUtil.parseJSONString(new ArrayList<Item>()));
            }
            List<Item> cart=JSONUtil.decode(jedis.get("cart"+token), new TypeReference<List<Item>>() {});
            cart.add(findById(itemId));
            jedis.set("cart"+token,JSONUtil.parseJSONString(cart));
            jedis.expire("cart"+token, Cons.Cart.TIME_OUT);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
        }

    }

    @Override
    public List<Item> getCart(String token) {
        Jedis jedis=jedisPool.getResource();
        try{
            if(jedis.get("cart"+token)==null){
                jedis.set("cart"+token,JSONUtil.parseJSONString(new ArrayList<Item>()));
            }
            List<Item> cart=JSONUtil.decode(jedis.get("cart"+token), new TypeReference<List<Item>>() {});
            return cart;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<Item>(0);
        }finally {
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
        }
    }

    @Override
    public boolean deleteItem(String token, String itemId) {
        Jedis jedis=jedisPool.getResource();
        try{
            if(jedis.get("cart"+token)==null){
                return false;
            }
            List<Item> cart=JSONUtil.decode(jedis.get("cart"+token), new TypeReference<List<Item>>() {});
            cart.remove(findById(itemId));
            jedis.set("cart"+token,JSONUtil.parseJSONString(cart));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
        }
    }
}
