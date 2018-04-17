package cn.breezefaith.service.impl;

import cn.breezefaith.service.IItemService;
import cn.breezefaith.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ItemServiceImplTest {

    @Resource(name = "itemService")
    private IItemService itemService;

    @Resource(name = "userService")
    private IUserService userService;

    @Test
    public void findAll() throws Exception {
//        List<Item> items=itemService.findAll();
//        List<Item> newItems=JSONUtil.decode(JSONUtil.parseJSONString(items), new TypeReference<List<Item>>() {});
//        System.out.println(newItems);
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

    @Test
    public void findById() throws Exception{
//        System.out.println(itemService.findById("1"));
    }

    @Test
    public void addToCart() throws Exception{
//        System.out.println(itemService.addToCart("1"));
    }

    @Test
    public void deleteItem() throws Exception{
//        String token=userService.login("admin","admin");
//        System.out.println(itemService.addToCart(token,"1"));
//        System.out.println(itemService.addToCart(token,"2"));
//        System.out.println(itemService.deleteItem(token,"1"));
//        userService.logout(token);
    }
}