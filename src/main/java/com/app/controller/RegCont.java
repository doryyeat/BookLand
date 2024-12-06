package com.app.controller;

import com.app.controller.global.Global;
import com.app.model.Users;
import com.app.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reg")
public class RegCont extends Global {

    @GetMapping
    public String reg(Model model) {
        addAttributes(model);
        return "reg";
    }

    @PostMapping
    public String regUser(Model model, @RequestParam String name, @RequestParam String username, @RequestParam String password) {
        if (usersRepo.findAll().isEmpty()) {
            usersRepo.save(new Users(name, username, password, Role.ADMIN));
            return "redirect:/login";
        }

        if (usersRepo.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с такой электронной почтой уже существует");
            addAttributes(model);
            return "reg";
        }

        usersRepo.save(new Users(name, username, password, Role.CLIENT));

        return "redirect:/login";
    }
}
