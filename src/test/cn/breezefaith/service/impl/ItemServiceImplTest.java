package cn.breezefaith.service.impl;

import cn.breezefaith.dao.IItemDao;
import cn.breezefaith.service.IItemService;
import cn.breezefaith.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ItemServiceImplTest {

    @Resource(name = "itemService")
    private IItemService itemService;

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name="itemDao")
    private IItemDao itemDao;

    @Resource(name="jedisPool")
    private JedisPool jedisPool;

//    @Test
    public void findAll() throws Exception {
//        List<Item> items=itemService.findAll();
//        List<Item> newItems=JSONUtil.decode(JSONUtil.parseJSONString(items), new TypeReference<List<Item>>() {});
//        System.out.println(newItems);
//        System.out.println(itemService.findAll());
//        for (Item item:itemService.findAll()){
//            System.out.println(item);
//        }
    }

//    @Test
    public void findAllInRedis() throws Exception {
//        List<Item> items=itemService.findAllInRedis();
//        System.out.println(items.get(0));
//        for (int i=0;i<items.size();i++){
//            System.out.println(items.get(i).getIid());
//        }
    }

//    @Test
    public void findById() throws Exception{
//        System.out.println(itemService.findById("1"));
    }

//    @Test
    public void addToCart() throws Exception{
//        System.out.println(itemService.addToCart("1"));
    }

//    @Test
    public void deleteItem() throws Exception{
//        String token=userService.login("admin","admin");
//        System.out.println(itemService.addToCart(token,"1"));
//        System.out.println(itemService.addToCart(token,"2"));
//        System.out.println(itemService.deleteItem(token,"1"));
//        userService.logout(token);
    }

    @Test
    public void getImageInMySQL(){
//        long begin=System.currentTimeMillis();
//        List<byte[]> bytesList=new LinkedList<>();
//        for (int i=1;i<4001;i++){
//            bytesList.add(itemService.getImageInMySQL(String.valueOf(i)));
//        }
//        System.out.println("bytesList's size="+bytesList.size());
//        long end=System.currentTimeMillis();
//        System.out.println("time:"+(end-begin)/1000.0+"s");
    }

    @Test
    public void getImageInRedis(){
//        long begin=System.currentTimeMillis();
//        List<byte[]> bytesList=new LinkedList<>();
//        for (int i=1;i<4001;i++){
//            bytesList.add(itemService.getImageInRedis(String.valueOf(i)));
//        }
//        System.out.println("bytesList's size="+bytesList.size());
//        long end=System.currentTimeMillis();
//        System.out.println("time:"+(end-begin)/1000.0+"s");
    }

    @Test
    public void getImageInURL(){
//        long begin=System.currentTimeMillis();
//        List<byte[]> bytesList=new LinkedList<>();
//        List<Item> items=itemService.findAll();
//        for (int i=1;i<1001;i++){
//            bytesList.add(itemService.getImageInURL(items.get(i-1).getItemImage()));
//        }
//        System.out.println("bytesList's size="+bytesList.size());
//        long end=System.currentTimeMillis();
//        System.out.println("time:"+(end-begin)/1000.0+"s");
    }

//    @Test
    public void test(){
//        Jedis jedis=jedisPool.getResource();
//
//
//        List<Item> items=itemService.findAllInRedis();
////        jedis.set("items", JSONUtil.parseJSONString(items));
//        for (Item item:items){
//            System.out.println(item.getItemImage());
//            String string=ImageUtil.encodeImgageToBase64(item.getItemImage());
//            jedis.set("image"+item.getIid(), string);
//
//            Item item1=new Item();
//            item1.setIid(item.getIid());
//            item1.setItemBytes(string);
//            if(itemDao.updateItem(item1)==1){
//                System.out.println(item.getIid()+"完成");
//            }
//
//
//        }
//        jedisPool.returnResource(jedis);
    }

//    @Test
    public void updateItem(){
        System.out.println(itemService.updateItem("1","item1","百货","第1件商品","http://192.168.181.134/zzcmall/img/1.JPG","2.20"));
    }
//    @Test
    public void addItem(){
        System.out.println(itemService.addItem("test","百货","第1件商品","http://192.168.181.134/zzcmall/img/1.JPG","2.20"));
    }
}