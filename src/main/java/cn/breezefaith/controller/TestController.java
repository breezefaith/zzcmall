package cn.breezefaith.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TestController extends AbstractController {

    @RequestMapping("/test.do")
    public String test(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("itemListInRedis",itemService.findAllInRedis());
        return "test";
    }
}
