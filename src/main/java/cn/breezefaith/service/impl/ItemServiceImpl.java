package cn.breezefaith.service.impl;

import cn.breezefaith.constant.Cons;
import cn.breezefaith.entity.Item;
import cn.breezefaith.service.AbstractService;
import cn.breezefaith.service.IItemService;
import cn.breezefaith.util.ImageUtil;
import cn.breezefaith.util.JSONUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

@Service("itemService")
public class ItemServiceImpl extends AbstractService implements IItemService {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public List<Item> findAll() {
        return itemDao.findAll();
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
    public int itemCount() {
        return itemDao.itemCount();
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
            Item item=findById(itemId);
            item.setItemBytes(null);
            cart.add(item);
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

    @Override
    public byte[] getImageInMySQL(String iid) {
        return ImageUtil.decodeBase64ToImage(itemDao.findById(iid).getItemBytes());
    }

    @Override
    public byte[] getImageInRedis(String iid) {
        Jedis jedis=jedisPool.getResource();
        byte[] imageStream=ImageUtil.decodeBase64ToImage(jedis.get("image"+iid));
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return imageStream;
    }

    @Override
    public byte[] getImageInURL(String url) {
        return ImageUtil.encodeImageToBytes(url);
    }

    @Override
    public boolean updateItem(String iid, String itemName, String itemCategory, String itemDescription, String itemImage, String itemPrice) {

        Item item=new Item();
        item.setIid(Integer.valueOf(iid));
        item.setItemName(itemName);
        item.setItemCategory(itemCategory);
        item.setItemDescription(itemDescription);
        item.setItemImage(itemImage);
        item.setItemPrice(Double.valueOf(itemPrice));
        Item temp=this.findById(iid);

        String bytes=null;
        if(temp!=null&&temp.getItemImage().equals(itemImage)==false){
            bytes=ImageUtil.encodeImgageToBase64(itemImage);
            item.setItemBytes(bytes);
        }else{
            item.setItemBytes(null);

        }
        if(itemDao.updateItemInfo(item)==true){
            if(temp!=null&&temp.getItemImage().equals(itemImage)==false){
                Jedis jedis=jedisPool.getResource();
                jedis.set("image"+iid,bytes);

                List<Item> items=JSONUtil.decode(jedis.get("items"), new TypeReference<List<Item>>() {});
                items.remove(Integer.valueOf(iid)-1);
                items.add(item.getIid()-2,item);
                jedis.set("items",JSONUtil.parseJSONString(items));
                jedisPool.returnResource(jedis);
            }
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteItemByAdmin(String iid) {
        if (itemDao.deleteItem(iid)==true){
            Jedis jedis=jedisPool.getResource();
            jedis.del("image"+iid);
            List<Item> items=JSONUtil.decode(jedis.get("items"), new TypeReference<List<Item>>() {});
            items.remove(Integer.valueOf(iid)-1);
            jedis.set("items",JSONUtil.parseJSONString(items));
            jedisPool.returnResource(jedis);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean addItem(String itemName, String itemCategory, String itemDescription, String itemImage, String itemPrice) {
        Item item=new Item();
        item.setItemName(itemName);
        item.setItemCategory(itemCategory);
        item.setItemImage(itemImage);
        item.setItemImage(itemImage);
        item.setItemPrice(Double.valueOf(itemPrice));
        item.setItemBytes(ImageUtil.encodeImgageToBase64(itemImage));
        if(itemDao.addItem(item)==true){
//            System.out.println(item.getIid());

            Jedis jedis=jedisPool.getResource();
            jedis.set("image"+item.getIid(),item.getItemBytes());
            List<Item> items=JSONUtil.decode(jedis.get("items"), new TypeReference<List<Item>>() {});
            item.setItemBytes(null);
            items.add(item);
            jedis.set("items",JSONUtil.parseJSONString(items));
            return true;
        }else {
            return false;
        }
    }


}
