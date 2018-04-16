package cn.breezefaith.controller;

import cn.breezefaith.constant.Cons;
import cn.breezefaith.util.JSONUtil;
import cn.breezefaith.util.ResponseVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class CartController extends AbstractController{
    @RequestMapping("cart.do")
    public String cart(HttpServletRequest request,HttpSession session){
        String token=(String)session.getAttribute("token");
        request.setAttribute("cart",itemService.getCart(token));
        return "cart";
    }

    @RequestMapping("addToCart.do")
    public void addToCart(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        ResponseVo responseVo=new ResponseVo();
        String token=(String)session.getAttribute("token");
        if(token!=null){
            if(itemService.addToCart(token,request.getParameter("itemId"))==true){
                responseVo.setSuccess(true);
                responseVo.setErrorCode(Cons.Request.REQUEST_SUCCESS_CODE);
                responseVo.setMessage(Cons.Request.REQUEST_SUCCESS_MESSAGE);
                responseVo.setData(null);
            }
        }else{
            responseVo.setSuccess(true);
            responseVo.setErrorCode(Cons.Request.REQUEST_SUCCESS_CODE);
            responseVo.setMessage(Cons.Request.REQUEST_SUCCESS_MESSAGE);
            responseVo.setData(null);
        }

        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }
}
