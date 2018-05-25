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
        response.setContentType("image/*");
        response.getOutputStream().write(itemService.getImage("image"+key));
        response.flushBuffer();
    }

    @RequestMapping("getImageByUrl.do")
    public void getImageByUrl(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String url=request.getParameter("url");
        response.setContentType("image/*");
        response.getOutputStream().write(itemService.getImageInURL(url));
        response.flushBuffer();
    }

    @RequestMapping("getImageInMySQL.do")
    public void getImageInMySQL(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String iid=request.getParameter("iid");
        response.setContentType("image/*");
        response.getOutputStream().write(itemService.getImageInMySQL(iid));
        response.flushBuffer();
    }
    @RequestMapping("getImageInRedis.do")
    public void getImageInRedis(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String iid=request.getParameter("iid");
        response.setContentType("image/*");
        response.getOutputStream().write(itemService.getImageInRedis(iid));
        response.flushBuffer();
    }

    @RequestMapping("getItemCount.do")
    public void getItemCount(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.getWriter().write(itemService.itemCount());
    }
}
