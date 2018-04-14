package cn.breezefaith.service.impl;

import cn.breezefaith.dao.IItemDao;
import cn.breezefaith.entity.Item;
import cn.breezefaith.service.IItemService;
import cn.breezefaith.util.ImageUtil;
import cn.breezefaith.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedList;
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
        return items;
    }

    @Override
    public List<Item> findAllInRedis(){
        Jedis jedis=jedisPool.getResource();
        try {
            return (List<Item>)JSONUtil.parseObject(jedis.get("items"),LinkedList.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] getImage(String key) {
        Jedis jedis=jedisPool.getResource();
        return ImageUtil.getBinaryBytes(jedis.get(key));
    }
}
