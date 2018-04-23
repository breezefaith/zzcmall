package cn.breezefaith.service.impl;

import cn.breezefaith.service.IRecordService;
import cn.breezefaith.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class RecordServiceImplTest{

    @Autowired
    private IUserService userService;

    @Autowired
    private IRecordService recordService;

    @Test
    public void findById() throws Exception {
//        System.out.println(recordService.findById("1"));
    }

    @Test
    public void findAll() throws Exception{
//        System.out.println(recordService.findAll());
    }

    @Test
    public void findByUid() throws Exception{
//        System.out.println(recordService.findByUid("2"));
    }

    @Test
    public void pay() throws Exception{
//        String token=userService.login("admin","admin");
//        System.out.println(recordService.pay(token,2,3,50.5));
//        userService.logout(token);
    }
}