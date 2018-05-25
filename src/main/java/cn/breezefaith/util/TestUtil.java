package cn.breezefaith.util;

import cn.breezefaith.dao.IItemDao;
import cn.breezefaith.entity.Item;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:jedis.xml");
        JedisPool jedisPool=(JedisPool) applicationContext.getBean("jedisPool");
        Jedis jedis=jedisPool.getResource();
        IItemDao itemDao=(IItemDao)applicationContext.getBean("itemDao");
        List<Item> items=new ArrayList<>();
        Item item1=new Item();
        item1.setIid(1);
        item1.setItemBytes("111111");
        Item item2=new Item();
        item2.setIid(2);
        item2.setItemBytes("111111");
        items.add(item1);
        items.add(item2);
        System.out.println(itemDao.updateItems(items));
        jedisPool.returnResource(jedis);
    }
}
