package cn.breezefaith.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class TestController extends AbstractController {

    @RequestMapping("/test.do")
    public String test(HttpServletRequest request){
        try {
            request.setAttribute("itemListInRedis",itemService.findAllInRedis());
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("itemListInRedis",null);
        }
        return "test";
    }

    @RequestMapping("/getImage.do")
    public void getImage(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String key=request.getParameter("itemId");
        response.setContentType("image/*");
        response.getOutputStream().write(itemService.getImage("image"+key));
        response.flushBuffer();
    }
}
