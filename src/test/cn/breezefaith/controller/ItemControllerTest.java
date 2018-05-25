package cn.breezefaith.controller;

import cn.breezefaith.service.IItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//@WebAppConfiguration
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-mvc-servlet.xml"})
public class ItemControllerTest {
    @Autowired
    private ItemController itemController;

    @Autowired
    private IItemService itemService;

    private MockMvc mockMvc;

    private int endIndex;

    @Before
    public void setUp(){
        mockMvc= MockMvcBuilders.standaloneSetup(itemController).build();
        endIndex=4000;
    }


    @Test
    public void getImageByUrlDirectly()throws Exception{
//        List<Item> items=itemService.findAllInRedis();
//        System.out.println("---------------------begin-------------------------");
//        long begin=System.currentTimeMillis();
//        for (Item item:items.subList(0,endIndex)){
//            CloseableHttpClient httpClient=HttpClients.createDefault();
//            HttpGet httpGet=new HttpGet(itemService.findById(String.valueOf(item.getIid())).getItemImage());
//            CloseableHttpResponse response=httpClient.execute(httpGet);
//            Assert.assertEquals(200,response.getStatusLine().getStatusCode());
//            response.close();
//            httpClient.close();
//            System.out.println("第"+item.getIid()+"张图片");
//        }
//        long end=System.currentTimeMillis();
//
//        System.out.println("耗时"+(end-begin)/1000.0+"秒");
//        System.out.println("---------------------end-------------------------");
    }

    @Test
    public void getImageByUrl() throws Exception {
//        System.out.println("---------------------begin-------------------------");
//        long begin=System.currentTimeMillis();
//        List<Item> items=itemService.findAllInRedis();
//        for (Item item:items.subList(0,endIndex)){
//            ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get("/getImageByUrl.do").param("url",item.getItemImage()));
//            String result=resultActions.andExpect(status().isOk())
//                    .andReturn()
//                    .getResponse()
//                    .getContentAsString();
//            System.out.println("第"+item.getIid()+"张图片");
//        }
//        long end=System.currentTimeMillis();
//        System.out.println("耗时"+(end-begin)/1000.0+"秒");
//        System.out.println("---------------------end-------------------------");

    }

    @Test
    public void getImageInMySQL() throws Exception {
//        System.out.println("---------------------begin-------------------------");
//        List<Item> items=itemService.findAll();
//        long begin=System.currentTimeMillis();
//        for (Item item:items.subList(0,endIndex)){
//            ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get("/getImageInMySQL.do").param("iid",String.valueOf(item.getIid())));
//            String result=resultActions.andExpect(status().isOk())
//                    .andReturn()
//                    .getResponse()
//                    .getContentAsString();
//            System.out.println("第"+item.getIid()+"张图片");
//        }
//        long end=System.currentTimeMillis();
//        System.out.println("耗时"+(end-begin)/1000.0+"秒");
//        System.out.println("---------------------end-------------------------");

    }

    @Test
    public void getImageInRedis() throws Exception {
//        System.out.println("---------------------begin-------------------------");
//        List<Item> items=itemService.findAllInRedis();
//        long begin=System.currentTimeMillis();
//        for (Item item:items.subList(0,endIndex)){
//            ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get("/getImageInRedis.do").param("iid",String.valueOf(item.getIid())));
//            String result=resultActions.andExpect(status().isOk())
//                    .andReturn()
//                    .getResponse()
//                    .getContentAsString();
//            System.out.println("第"+item.getIid()+"张图片");
//        }
//        long end=System.currentTimeMillis();
//        System.out.println("耗时"+(end-begin)/1000.0+"秒");
//        System.out.println("---------------------end-------------------------");
    }

//    @Test
    public void test()throws Exception{
//        System.out.println("---------------------begin-------------------------");
//        long begin=System.currentTimeMillis();
//        //创建一个httpclient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        //创建一个GET对象
//        HttpGet get =new HttpGet("https://www.baidu.com");
//        //执行请求
//        CloseableHttpResponse response =httpClient.execute(get);
//        //取响应的结果
//        int statusCode =response.getStatusLine().getStatusCode();
//        System.out.println(statusCode);
//
//        long end=System.currentTimeMillis();
//        System.out.println("耗时"+(end-begin)/1000.0+"秒");
//        System.out.println("---------------------end-------------------------");
    }
}