package cn.breezefaith.service.impl;

import cn.breezefaith.dao.IUserDao;
import cn.breezefaith.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceImplTest {

    @Resource(name="userService")
    private IUserService userService;

    @Resource(name="jedisPool")
    private JedisPool jedisPool;

    @Resource(name="userDao")
    private IUserDao userDao;

    @Test
    public void login() throws Exception {
//        String token=userService.login("admin","admin");

//        System.out.println(userService.getPersonInfo(token));
    }

    @Test
    public void register() throws Exception {
//        System.out.println(userService.register("test2","test2","18251958031","test2"));
    }

    @Test
    public void checkAvailable() throws Exception {
//        System.out.println(userService.checkUsernameAvailable("test"));
//        System.out.println(userService.checkPhoneAvailable("18251958027"));
//        System.out.println(userService.checkEmailAvailable("921494701@qq.com"));
    }

    @Test
    public void updatePersonInfo() throws Exception{
//        String token=userService.login("admin","admin");
//        System.out.println(userService.updatePersonInfo(token,null,"1@njupt.edu.cn"));

    }

    @Test
    public void updatePassword() throws Exception{
//        String token=userService.login("admin","admin");
//        System.out.println(userService.isLogged(token));
//        System.out.println(userService.updatePassword(token,null,"1111"));
//        userService.logout(token);
//        System.out.println(userService.isLogged(token));
    }

}