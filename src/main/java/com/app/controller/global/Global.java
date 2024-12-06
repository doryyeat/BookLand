package com.app.controller.global;

import com.app.model.Users;
import com.app.model.enums.Category;
import com.app.model.enums.Genre;
import com.app.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Global {
    @Autowired
    protected UsersRepo usersRepo;
    @Autowired
    protected BooksRepo booksRepo;
    @Autowired
    protected BooksDescriptionRepo booksDescriptionRepo;
    @Autowired
    protected StatisticsRepo statisticsRepo;

    protected Users getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return usersRepo.findByUsername(userDetail.getUsername());
        }
        return null;
    }

    protected String getRole() {
        Users users = getUser();
        if (users == null) return "NOT";
        return users.getRole().toString();
    }

    protected void addAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
        model.addAttribute("categories", Category.values());
        model.addAttribute("genres", Genre.values());
    }


    protected String uploadImg = uploadPath + "/img/";

    public static String uploadPath = getUploadPath();

    private static String getUploadPath() {
        StringBuilder dir = new StringBuilder(System.getProperty("user.dir"));
        for (int j = 0; j < dir.length(); j++) {
            if (dir.charAt(j) == '\\') {
                dir.setCharAt(j, '/');
            }
        }

        if (dir.toString().startsWith("/application")) {
            dir.append("/BOOT-INF/classes");
        } else {
            dir.append("/src/main/resources");
        }

        return dir.toString();
    }

    public static String saveFile(MultipartFile file, String path) throws IOException {
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            String result = path + "/" + uuidFile + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/img/" + result));
            return "/img/" + result;
        } else throw new IOException();
    }

    public static float round(float value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (float) tmp / factor;
    }

    private static String getDatetime() {
        return LocalDateTime.now().toString();
    }

    public static String getDateNow() {
        return getDatetime().substring(0, 10);
    }

    public static String getTimeNow() {
        return getDatetime().substring(11, 19);
    }

    public static String getDateAndTimeNow() {
        return getDateNow() + " " + getTimeNow();
    }

}