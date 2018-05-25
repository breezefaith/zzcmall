package cn.breezefaith.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

@WebAppConfiguration
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserControllerTest {

    @Resource(name = "userController")
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void updatePassword() throws Exception {
//        ResultActions resultActions=mockMvc.perform(
//                MockMvcRequestBuilders.get("/updatePassword")
//                        .param("origin","origin")
//                        .param("new","new")
//        );
//        String responseString=resultActions
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(responseString);
    }

}