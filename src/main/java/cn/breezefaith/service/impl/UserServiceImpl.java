package cn.breezefaith.service.impl;

import cn.breezefaith.constant.Cons;
import cn.breezefaith.dao.IUserDao;
import cn.breezefaith.entity.User;
import cn.breezefaith.service.IUserService;
import cn.breezefaith.util.JSONUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Resource(name="userDao")
    private IUserDao userDao;

    @Autowired
    private JedisPool jedisPool;

    private static Logger logger=Logger.getLogger(UserServiceImpl.class);

    @Override
    public String login(String username, String password) {

        User user=userDao.login(username,password);
        //账号或密码错误则返回null
        if(user==null){
            return null;
        }
        String token=UUID.randomUUID().toString();

        Jedis jedis=jedisPool.getResource();
        jedis.set(token, JSONUtil.parseJSONString(user));
        jedis.expire(token, Cons.TOKEN_TIMEOUT_SECONDS);
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return token;
    }

    @Override
    public String register(String username, String password, String phone, String email){
        try {
            if(userDao.register(username,password,phone,email)==0){
//                logger.info("register failed");
                return null;
            }else{
                Jedis jedis=jedisPool.getResource();
                String token=UUID.randomUUID().toString();
                User user=new User();
                user.setEmail(email);
                user.setUsername(username);
                user.setPassword(password);
                user.setPhone(phone);
                jedis.set(token,JSONUtil.parseJSONString(user));
//                logger.info(token);
//                logger.info(user);
                if(jedis!=null){
                    jedisPool.returnResource(jedis);
                }
                return token;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean checkUsernameAvailable(String username){
        if(userDao.checkUsernameAvailable(username)==null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean checkPhoneAvailable(String phone) {
        if (userDao.checkPhoneAvailable(phone)==null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean checkEmailAvailable(String email) {
        if (userDao.checkEmailAvailable(email)==null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User getPersonInfo(String token) throws IOException {
        Jedis jedis=jedisPool.getResource();
        User user=(User)JSONUtil.parseObject(jedis.get(token),User.class);
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return user;
    }

    @Override
    public boolean updatePersonInfo(String token, String phone, String email) throws IOException {
        Jedis jedis=jedisPool.getResource();
        User user = (User) JSONUtil.parseObject(jedis.get(token), User.class);
        if(email!=null){
            user.setEmail(email);
        }
        if(phone!=null) {
            user.setPhone(phone);
        }
        if(userDao.updatePersonInfo(user)==true){
            jedis.set(token,JSONUtil.parseJSONString(user));
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
            return true;
        }else{
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
            return false;
        }
    }

    @Override
    public boolean updatePassword(String token, String originPassword, String newPassword) throws IOException {
        Jedis jedis=jedisPool.getResource();
        User user = (User) JSONUtil.parseObject(jedis.get(token), User.class);
        if(originPassword!=null&&newPassword!=null&&originPassword.equals("")==false&&newPassword.equals("")==false){
            if(originPassword.equals(user.getPassword())){
                user.setPassword(newPassword);
                if(userDao.updatePersonInfo(user)==true){
                    jedis.set(token,JSONUtil.parseJSONString(user));
                    if(jedis!=null){
                        jedisPool.returnResource(jedis);
                    }
                    return true;
                }
                if(jedis!=null){
                    jedisPool.returnResource(jedis);
                }
            }
        }
        return false;
    }

    @Override
    public User isLogged(String token) throws IOException {
        Jedis jedis=jedisPool.getResource();
        try {
            User user=(User)JSONUtil.parseObject(jedis.get(token),User.class);
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
            return user;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void logout(String token) {
        Jedis jedis=jedisPool.getResource();
        jedis.del(token);
        jedis.del("cart"+token);
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
    }

}
