package cn.breezefaith.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class BasicController extends AbstractController{
    @RequestMapping("/hello.do")
    public void hello(HttpServletResponse response) {
        response.setContentType("text/html;character=utf8");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write("<html><head><title>hello</title></head><body><h1>你好，这里是首页</h1></body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/index.do")
    public String index(HttpServletRequest request){
        request.setAttribute("itemList",itemService.findAllInRedis().subList(0,500));
        return "index";
    }

    @RequestMapping("/login-page.do")
    public String login(){
        return "login";
    }

    @RequestMapping("/register-page.do")
    public String register(){
        return "register";
    }

    @RequestMapping("/forget-page.do")
    public String forget(){
        return "forget";
    }

    @RequestMapping("/getImageInMySQL-page.do")
    public String getImageInMySQLPage(HttpServletRequest request){
        request.setAttribute("itemList",itemService.findAll());
        return "getImageInMySQL-page";
    }

    @RequestMapping("/getImageInRedis-page.do")
    public String getImageInRedisPage(HttpServletRequest request){
        request.setAttribute("itemList",itemService.findAllInRedis());
        return "getImageInRedis-page";
    }

    @RequestMapping("/getImageInURL-page.do")
    public String getImageInURLPage(HttpServletRequest request){
        request.setAttribute("itemList",itemService.findAllInRedis());
        return "getImageInURL-page";
    }

    @RequestMapping("/getImageInURLDirectly-page.do")
    public String getImageInURLDirectly(HttpServletRequest request){
        request.setAttribute("itemList",itemService.findAll());
        return "getImageInURLDirectly-page";
    }
}
