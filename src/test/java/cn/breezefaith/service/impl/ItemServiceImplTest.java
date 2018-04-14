package cn.breezefaith.service.impl;

import cn.breezefaith.entity.Item;
import cn.breezefaith.service.IItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ItemServiceImplTest {

    @Resource(name = "itemService")
    private IItemService itemService;

    @Test
    public void findAll() throws Exception {
        for (Item item:itemService.findAll()){
            System.out.println(item);
        }
    }

    @Test
    public void findAllInRedis() throws Exception {
        System.out.println(itemService.findAllInRedis());
    }
}