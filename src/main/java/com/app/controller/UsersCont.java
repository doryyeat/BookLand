package com.app.controller;

import com.app.controller.global.Global;
import com.app.model.Books;
import com.app.model.Users;
import com.app.model.enums.BookStatus;
import com.app.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersCont extends Global {
    @GetMapping
    public String subs(Model model) {
        addAttributes(model);
        model.addAttribute("users", usersRepo.findAll());
        model.addAttribute("roles", Role.values());
        return "users";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @RequestParam Role role) {
        Users user = usersRepo.getReferenceById(id);
        user.setRole(role);
        if (user.getRole() == Role.ADMIN) {
            for (Books book : user.getBooks()) {
                book.setUser(null);
                book.setStatus(BookStatus.ACTIVE);
                book.setDateFree("");
                booksRepo.save(book);
            }
        }
        usersRepo.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        Users user = usersRepo.getReferenceById(id);
        for (Books book : user.getBooks()) {
            book.setUser(null);
            book.setStatus(BookStatus.ACTIVE);
            book.setDateFree("");
            booksRepo.save(book);
        }
        usersRepo.deleteById(id);
        return "redirect:/users";
    }
}
