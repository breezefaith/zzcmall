package cn.breezefaith.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestControllerTest {
    @Autowired
    private TestController testController;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
//        mockMvc= MockMvcBuilders.standaloneSetup(testController).build();
    }

    @Test
    public void getPicture() throws Exception {
//        ResultActions resultActions=mockMvc.perform(MockMvcRequestBuilders.get("getPicture.do"));
//        MvcResult mvcResult=resultActions.andReturn();
//        String result=mvcResult.getResponse().getContentAsString();
//        System.out.println(result);
    }

}