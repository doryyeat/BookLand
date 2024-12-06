package com.app.controller;

import com.app.controller.global.Global;
import com.app.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountCont extends Global {

    @GetMapping
    public String account(Model model) {
        addAttributes(model);
        return "account";
    }

    @PostMapping("/update/name")
    public String updateName(@RequestParam String name) {
        Users user = getUser();
        user.setName(name);
        usersRepo.save(user);
        return "redirect:/account";
    }

    @PostMapping("/update")
    public String update(
            @RequestParam(defaultValue = "false") boolean fantasy,
            @RequestParam(defaultValue = "false") boolean horror, @RequestParam(defaultValue = "false") boolean detective,
            @RequestParam(defaultValue = "false") boolean romance, @RequestParam(defaultValue = "false") boolean psychology, @RequestParam(defaultValue = "false") boolean classic
    ) {
        Users user = getUser();

        user.update(fantasy, horror, detective, romance, psychology, classic);

        usersRepo.save(user);
        return "redirect:/account";
    }

}
