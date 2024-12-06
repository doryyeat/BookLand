package com.app.controller;

import com.app.controller.global.Global;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCont extends Global {

    @GetMapping
    public String index1() {
        return "redirect:/books";
    }

    @GetMapping("/index")
    public String index2() {
        return "redirect:/books";
    }

}
