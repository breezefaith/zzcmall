package cn.breezefaith.service.impl;

import cn.breezefaith.dao.IAddressDao;
import cn.breezefaith.service.IAddressService;
import cn.breezefaith.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AddressServiceImplTest {
    @Resource(name="jedisPool")
    private JedisPool jedisPool;

    @Resource(name = "addressDao")
    private IAddressDao addressDao;

    @Resource(name = "addressService")
    private IAddressService addressService;

    @Resource(name = "userService")
    private IUserService userService;

    @Test
    public void deleteAddress() throws Exception{
//        String token=userService.login("admin","admin");
//        System.out.println(addressService.deleteAddress(token,9));
//        userService.logout(token);
    }

    @Test
    public void addAddress() throws Exception{
//        String token=userService.login("admin","admin");
//        System.out.println(addressService.addAddress(token,"test","18251958027","210000","testAddress"));
    }

    @Test
    public void updateAddress() throws Exception{
//        String token=userService.login("admin","admin");
//        System.out.println(addressService.updateAddress(token,13,"test","18251958027","210000","testAddress"));

    }
}