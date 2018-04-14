package cn.breezefaith.service.impl;

import cn.breezefaith.entity.Item;
import cn.breezefaith.service.IItemService;
import cn.breezefaith.util.JSONUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.List;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ItemServiceImplTest {

    @Resource(name = "itemService")
    private IItemService itemService;

    @Test
    public void findAll() throws Exception {
        List<Item> items=itemService.findAll();
        List<Item> newItems=JSONUtil.decode(JSONUtil.parseJSONString(items), new TypeReference<List<Item>>() {});
        System.out.println(newItems);
//        System.out.println(itemService.findAll());
//        for (Item item:itemService.findAll()){
//            System.out.println(item);
//        }
    }

    @Test
    public void findAllInRedis() throws Exception {
//        List<Item> items=itemService.findAllInRedis();
//        System.out.println(items.getClass().getName());
//        for (int i=0;i<items.size();i++){
//            System.out.println(items.get(i).getIid());
//        }
    }
}