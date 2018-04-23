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

    @RequestMapping("deleteItem.do")
    public void deleteItem(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        ResponseVo responseVo=new ResponseVo();
        String token=(String)session.getAttribute("token");
        if(token!=null){
            if(itemService.deleteItem(token,request.getParameter("itemId"))==true){
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
        response.setContentType("text/json;charset=utf8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }

    @RequestMapping("checkOutPage.do")
    public String checkOutPage(HttpServletRequest request){
        request.setAttribute("counts",request.getParameter("counts"));
        request.setAttribute("cost",request.getParameter("cost"));
        request.setAttribute("addresses",userService.getAddresses((String)request.getSession().getAttribute("token")));
        return "checkOutPage";
    }

    @RequestMapping("pay.do")
    public void pay(HttpServletRequest request,HttpServletResponse response) throws IOException{
        ResponseVo responseVo=new ResponseVo();
        Integer counts=Integer.valueOf(request.getParameter("counts"));
        Double cost=Double.valueOf(request.getParameter("cost"));
        Integer aid=Integer.valueOf(request.getParameter("aid"));
        String token=(String)request.getSession().getAttribute("token");
        if(token!=null&&recordService.pay(token,aid,counts,cost)==true){
            responseVo.setData(null);
            responseVo.setMessage(Cons.Request.REQUEST_SUCCESS_MESSAGE);
            responseVo.setErrorCode(Cons.Request.REQUEST_SUCCESS_CODE);
            responseVo.setSuccess(true);
        }else{
            responseVo.setData(null);
            responseVo.setMessage(Cons.Request.REQUEST_FAILED_MESSAGE);
            responseVo.setErrorCode(Cons.Request.REQUEST_FAILED_CODE);
            responseVo.setSuccess(false);
        }
        response.setContentType("text/json;charset=utf8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }
}
