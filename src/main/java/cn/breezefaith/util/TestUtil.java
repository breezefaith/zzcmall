package cn.breezefaith.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestUtil {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:jedis.xml");
        JedisPool jedisPool=(JedisPool) applicationContext.getBean("jedisPool");
        Jedis jedis=jedisPool.getResource();
        jedis.set("test_key","test_value");
    }
}
