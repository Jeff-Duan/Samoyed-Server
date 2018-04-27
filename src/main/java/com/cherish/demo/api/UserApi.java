package com.cherish.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    @GetMapping(value = "/info")
    public String info(HttpSession session) {
        Optional<String> userOptional = Optional.ofNullable((String) session.getAttribute("User"));
        return userOptional.orElse(null);
    }

}
