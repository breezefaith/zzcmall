package cn.breezefaith.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ItemController extends AbstractController {
    @RequestMapping("/item.do")
    public String itemDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String itemId=request.getParameter("itemId");
        request.setAttribute("item",itemService.findById(itemId));
        return "item";
    }

    @RequestMapping("/getImage.do")
    public void getImage(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String key=request.getParameter("itemId");
//        response.setContentType("image/*");
        response.getOutputStream().write(itemService.getImage("image"+key));
        response.flushBuffer();
    }
}
