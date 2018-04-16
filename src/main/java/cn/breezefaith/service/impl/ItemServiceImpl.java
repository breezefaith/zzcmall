package cn.breezefaith.service.impl;

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
    public boolean addToCart(String itemId) {
        Jedis jedis=jedisPool.getResource();
        try{
            if(jedis.get("cart")==null){
                jedis.set("cart",JSONUtil.parseJSONString(new ArrayList<Item>()));
            }
            List<Item> cart=JSONUtil.decode(jedis.get("cart"), new TypeReference<List<Item>>() {});
            cart.add((Item)JSONUtil.parseObject(jedis.get("item"+itemId),Item.class));
            jedis.set("cart",JSONUtil.parseJSONString(cart));
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
