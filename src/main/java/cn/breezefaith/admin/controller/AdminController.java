package cn.breezefaith.admin.controller;

import cn.breezefaith.admin.service.AdminService;
import cn.breezefaith.constant.Cons;
import cn.breezefaith.controller.AbstractController;
import cn.breezefaith.util.JSONUtil;
import cn.breezefaith.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login-page.do")
    public String loginPage(){
        return "admin/login";
    }

    @RequestMapping("/login.do")
    public void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String token = adminService.login(username, password);
        ResponseVo responseVo = new ResponseVo();
        if (token == null) {
            responseVo.setSuccess(false);
            responseVo.setData(null);
            responseVo.setErrorCode(1001);
            responseVo.setMessage("密码错误");
        } else {
            responseVo.setSuccess(true);
            responseVo.setData(token);
            responseVo.setErrorCode(0);
            responseVo.setMessage("登录成功");
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
            responseVo.setErrorCode(1002);
            responseVo.setMessage(Cons.Login.NOT_LOGGED_MESSAGE);
            responseVo.setData(null);
        }else{
            responseVo.setSuccess(true);
            responseVo.setErrorCode(0);
            responseVo.setMessage(Cons.Login.LOGIN_SUCCESS_MESSAGE);
            responseVo.setData(adminService.isLogged(token));
        }
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }

    @RequestMapping("/logout.do")
    public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String token=(String)session.getAttribute("token");
        if(token!=null){
            adminService.logout(token);
            session.removeAttribute("token");
        }
        response.sendRedirect("login.do");
    }

    @RequestMapping("main.do")
    public String main(){
        return "admin/main";
    }

    @RequestMapping("records.do")
    public String records(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        session.setAttribute("records",recordService.findAll());
        return "admin/records";
    }

    @RequestMapping("items.do")
    public String items(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        session.setAttribute("items",itemService.findAllInRedis());
        return "admin/items";
    }

    @RequestMapping("updateCourierNumber.do")
    public void updateCourierNumber(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        ResponseVo responseVo = new ResponseVo();
        if(recordService.updateRecord(request.getParameter("rid"),request.getParameter("courier"))==true){
            responseVo.setSuccess(true);
            responseVo.setErrorCode(0);
            responseVo.setMessage("更新成功");
            responseVo.setData(null);
        }else{
            responseVo.setSuccess(false);
            responseVo.setErrorCode(Cons.Request.REQUEST_FAILED_CODE);
            responseVo.setMessage("更新失败");
            responseVo.setData(null);
        }
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }

    @RequestMapping("updateItem.do")
    public void updateItem(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        ResponseVo responseVo = new ResponseVo();
        if(itemService.updateItem(request.getParameter("iid"),request.getParameter("itemName"),request.getParameter("itemCategory"),request.getParameter("itemDescription"),request.getParameter("itemImage"),request.getParameter("itemPrice"))){
            responseVo.setSuccess(true);
            responseVo.setErrorCode(0);
            responseVo.setMessage("更新成功");
            responseVo.setData(null);
        }else{
            responseVo.setSuccess(false);
            responseVo.setErrorCode(Cons.Request.REQUEST_FAILED_CODE);
            responseVo.setMessage("更新失败");
            responseVo.setData(null);
        }
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }

    @RequestMapping("addItem.do")
    public void addItem(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        ResponseVo responseVo = new ResponseVo();
        if(itemService.addItem(request.getParameter("itemName"),request.getParameter("itemCategory"),request.getParameter("itemDescription"),request.getParameter("itemImage"),request.getParameter("itemPrice"))){
            responseVo.setSuccess(true);
            responseVo.setErrorCode(0);
            responseVo.setMessage("添加成功");
            responseVo.setData(null);
        }else{
            responseVo.setSuccess(false);
            responseVo.setErrorCode(Cons.Request.REQUEST_FAILED_CODE);
            responseVo.setMessage("添加失败");
            responseVo.setData(null);
        }
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }

    @RequestMapping("deleteItem.do")
    public void deleteItem(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        ResponseVo responseVo = new ResponseVo();
        if(itemService.deleteItemByAdmin(request.getParameter("iid"))){
            responseVo.setSuccess(true);
            responseVo.setErrorCode(0);
            responseVo.setMessage("删除成功");
            responseVo.setData(null);
        }else{
            responseVo.setSuccess(false);
            responseVo.setErrorCode(Cons.Request.REQUEST_FAILED_CODE);
            responseVo.setMessage("删除失败");
            responseVo.setData(null);
        }
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONUtil.parseJSONString(responseVo));
    }
}
