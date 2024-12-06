package com.app.controller;

import com.app.controller.global.Global;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutCont extends Global {
    @GetMapping("/about")
    public String about(Model model) {
        addAttributes(model);
        return "about";
    }

    @GetMapping("/info")
    public String info(Model model) {
        addAttributes(model);
        return "info";
    }

}
