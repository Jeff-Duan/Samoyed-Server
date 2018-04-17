package com.cherish.demo.api;

import com.cherish.demo.entity.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/test")
public class TestApi {

    @GetMapping(value = "/init")
    public void initSession(HttpSession httpSession) {
        System.out.println("init session");
        User user = new User();
        user.setUserId(1);
        httpSession.setAttribute("User", user);
    }

    @GetMapping(value = "/destroy")
    public void destroySession(HttpSession httpSession) {
        System.out.println("destroy session");
        httpSession.invalidate();
    }

    @GetMapping(value = "/test")
    public String test(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("User");
        if (user != null) {
            System.out.println("User存在,"+"userId="+user.getUserId());
            return String.valueOf(user.getUserId());
        }else{
            System.out.println("User不存在");
        }
        return "";
    }

}
