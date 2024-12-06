package com.app.controller;

import com.app.controller.global.Global;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginCont extends Global {

    @GetMapping
    public String login(Model model) {
        addAttributes(model);
        return "login";
    }
}
