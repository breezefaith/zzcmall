package cn.breezefaith.controller;

import cn.breezefaith.constant.Cons;
import cn.breezefaith.util.JSONUtil;
import cn.breezefaith.util.ResponseVo;
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

    @RequestMapping("addToCart.do")
    public void addToCart(HttpServletRequest request,HttpServletResponse response) throws IOException{
        ResponseVo responseVo=new ResponseVo();
        if(itemService.addToCart(request.getParameter("itemId"))==true){
            responseVo.setSuccess(true);
            responseVo.setErrorCode(Cons.Request.REQUEST_SUCCESS_CODE);
            responseVo.setMessage(Cons.Request.REQUEST_SUCCESS_MESSAGE);
            responseVo.setData(null);
        }else{
            responseVo.setSuccess(true);
            responseVo.setErrorCode(Cons.Request.REQUEST_SUCCESS_CODE);
            responseVo.setMessage(Cons.Request.REQUEST_SUCCESS_MESSAGE);
            responseVo.setData(null);
        }
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }
}
