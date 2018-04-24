package cn.breezefaith.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RecordController extends AbstractController {
    @RequestMapping("records.do")
    public String records(HttpServletRequest request){
        String token=(String)request.getSession().getAttribute("token");
        if(token!=null){
            request.setAttribute("records",recordService.findByUser(token));
        }
        return "records";
    }
}
