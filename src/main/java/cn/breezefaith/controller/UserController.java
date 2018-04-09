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
public class UserController extends AbstractController {

    private void setRequestResult(ResponseVo responseVo,boolean result){
        if(result==true){
            responseVo.setSuccess(true);
            responseVo.setErrorCode(Cons.Request.REQUEST_SUCCESS_CODE);
            responseVo.setMessage(Cons.Request.REQUEST_SUCCESS_MESSAGE);
            responseVo.setData(null);
        }else {
            responseVo.setSuccess(false);
            responseVo.setErrorCode(Cons.Request.REQUEST_FAILED_CODE);
            responseVo.setMessage(Cons.Request.REQUEST_FAILED_MESSAGE);
            responseVo.setData(null);
        }
    }

    @RequestMapping("/login.do")
    public void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String token = userService.login(username, password);
        ResponseVo responseVo = new ResponseVo();
        if (token == null) {
            responseVo.setSuccess(false);
            responseVo.setData(null);
            responseVo.setErrorCode(Cons.Login.USERNAME_OR_PASSWORD_WRONG_CODE);
            responseVo.setMessage(Cons.Login.USERNAME_OR_PASSWORD_WRONG_MESSAGE);
        } else {
            responseVo.setSuccess(true);
            responseVo.setData(token);
            responseVo.setErrorCode(Cons.Login.LOGIN_SUCCESS_CODE);
            responseVo.setMessage(Cons.Login.LOGIN_SUCCESS_MESSAGE);
            session.setAttribute("token", token);
        }
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }

    @RequestMapping("/isLogged.do")
    public void isLogged(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        ResponseVo responseVo = new ResponseVo();
        String token=(String)session.getAttribute("token");
        if(token==null){
            responseVo.setSuccess(false);
            responseVo.setErrorCode(Cons.Login.NOT_LOGGED_CODE);
            responseVo.setMessage(Cons.Login.NOT_LOGGED_MESSAGE);
            responseVo.setData(null);
        }else{
            responseVo.setSuccess(true);
            responseVo.setErrorCode(Cons.Login.LOGIN_SUCCESS_CODE);
            responseVo.setMessage(Cons.Login.LOGIN_SUCCESS_MESSAGE);
            responseVo.setData(userService.isLogged(token));
        }
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }

    @RequestMapping("/logout.do")
    public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String token=(String)session.getAttribute("token");
        if(token!=null){
            userService.logout(token);
            session.removeAttribute("token");
        }
        response.sendRedirect("index.do");
    }

    @RequestMapping("/register.do")
    public void register(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
        ResponseVo responseVo=new ResponseVo();
        String token=userService.register(request.getParameter("username"),request.getParameter("password"),request.getParameter("phone"),request.getParameter("email"));
        if (token==null){
            responseVo.setSuccess(false);
            responseVo.setErrorCode(Cons.Register.REGISTER_FAILED_CODE);
            responseVo.setMessage(Cons.Register.REGISTER_FAILED_MESSAGE);
            responseVo.setData(null);
        }else{
            responseVo.setSuccess(true);
            responseVo.setErrorCode(Cons.Register.REGISTER_SUCCESS_CODE);
            responseVo.setMessage(Cons.Register.REGISTER_SUCCESS_MESSAGE);
            responseVo.setData(token);
            session.setAttribute("token",token);
        }
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }

    @RequestMapping("/register-success.do")
    public String registerSuccess(){
        return "register-success";
    }

    @RequestMapping("/person-center.do")
    public String personCenter(){
        return "person-center";
    }

    @RequestMapping("getPersonInfo.do")
    public void getPersonInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
        ResponseVo responseVo=new ResponseVo();
        String token=session.getAttribute("token").toString();
        if (token==null){
            responseVo.setSuccess(false);
            responseVo.setErrorCode(Cons.Request.REQUEST_FAILED_CODE);
            responseVo.setMessage(Cons.Request.REQUEST_FAILED_MESSAGE);
            responseVo.setData(null);
        }else{
            responseVo.setSuccess(true);
            responseVo.setErrorCode(Cons.Request.REQUEST_SUCCESS_CODE);
            responseVo.setMessage(Cons.Request.REQUEST_SUCCESS_MESSAGE);
            responseVo.setData(userService.getPersonInfo(token));
            session.setAttribute("token",token);
        }
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }

    @RequestMapping("updatePersonInfo.do")
    public void updatePersonInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        ResponseVo responseVo = new ResponseVo();
        String token = session.getAttribute("token").toString();
        boolean result=userService.updatePersonInfo(token,request.getParameter("phone"),request.getParameter("email"));
        setRequestResult(responseVo,result);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }

    @RequestMapping("updatePassword.do")
    public void updatePassword(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        ResponseVo responseVo = new ResponseVo();
        String token = session.getAttribute("token").toString();
        boolean result=userService.updatePassword(token,request.getParameter("origin"),request.getParameter("new"));
        setRequestResult(responseVo,result);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }

    @RequestMapping("deleteAddress.do")
    public void deleteAddress(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
        ResponseVo responseVo = new ResponseVo();
        String token = session.getAttribute("token").toString();
        boolean result=addressService.deleteAddress(token,Integer.valueOf(request.getParameter("aid")));
        setRequestResult(responseVo,result);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }

    @RequestMapping("addAddress.do")
    public void addAddress(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
        ResponseVo responseVo = new ResponseVo();
        String token = session.getAttribute("token").toString();
        boolean result=addressService.addAddress(token,request.getParameter("name"),request.getParameter("phone"),request.getParameter("postCode"),request.getParameter("address"));
        setRequestResult(responseVo,result);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }

    @RequestMapping("updateAddress.do")
    public void updateAddress(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
        ResponseVo responseVo = new ResponseVo();
        String token = session.getAttribute("token").toString();
        boolean result=addressService.updateAddress(token,Integer.valueOf(request.getParameter("aid")),request.getParameter("name"),request.getParameter("phone"),request.getParameter("postCode"),request.getParameter("address"));
        setRequestResult(responseVo,result);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }
}
