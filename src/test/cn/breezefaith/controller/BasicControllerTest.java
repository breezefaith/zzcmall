package cn.breezefaith.controller;

import cn.breezefaith.service.IItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@WebAppConfiguration
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BasicControllerTest {

    @Autowired
    HttpSession httpSession;

    @Autowired
    private IItemService itemService;

    @Resource(name = "basicController")
    private BasicController basicController;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc= MockMvcBuilders.standaloneSetup(basicController).build();
        httpSession.setAttribute("itemList",itemService.findAllInRedis());
    }

    @Test
    public void getImageInMySQLPage() throws Exception {
    }

    @Test
    public void getImageInRedisPage() throws Exception {
//        long begin=System.currentTimeMillis();
//        ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get("/getImageInRedis-page.do"));
//        String responseString=resultActions
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println("--------返回的json = " + responseString);
//        long end=System.currentTimeMillis();
//        System.out.println("time:"+(end-begin)/1000.0+"s");
    }

    @Test
    public void getImageInURLPage() throws Exception {
    }

    @Test
    public void getImageInURLDirectlyPage() throws Exception {

    }

}