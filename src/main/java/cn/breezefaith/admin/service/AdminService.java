package cn.breezefaith.admin.service;

import cn.breezefaith.admin.entity.Admin;
import cn.breezefaith.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    private JedisPool jedisPool;

    public String login(String username, String password) {
        if(Admin.getInstance().isOK(username,password)==true){
            Jedis jedis=jedisPool.getResource();
            String token= UUID.randomUUID().toString();
            jedis.set(token, JSONUtil.parseJSONString("admin"));
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
            return token;
        }else{
            return null;
        }
    }

    public String isLogged(String token) throws IOException {
        Jedis jedis=jedisPool.getResource();
        try {
            String admin=jedis.get(token);
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
            return admin;
        }catch (Exception e){
            return null;
        }
    }

    public void logout(String token) {
        Jedis jedis=jedisPool.getResource();
        jedis.del(token);
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
    }
}
