package com.app.controller;

import com.app.controller.global.Global;
import com.app.model.Statistics;
import com.app.model.enums.Category;
import com.app.model.enums.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/statistics")
public class StatsCont extends Global {
    @GetMapping
    public String Statistics(Model model) {
        addAttributes(model);

        List<Statistics> statistics = statisticsRepo.findAll();
        int income = statistics.stream().reduce(0, (i, s) -> i + (s.getDays()), Integer::sum);
        model.addAttribute("statistics", statistics);
        model.addAttribute("income", income);

        Genre[] genres = Genre.values();

        String[] genresString = new String[genres.length];
        int[] genresInt = new int[genres.length];

        for (int i = 0; i < genres.length; i++) {
            genresString[i] = genres[i].getName();
            genresInt[i] = booksRepo.findAllByDescription_Genre(genres[i]).size();
        }

        model.addAttribute("genresString", genresString);
        model.addAttribute("genresInt", genresInt);

        Category[] categories = Category.values();

        String[] categoriesString = new String[categories.length];
        int[] categoriesInt = new int[categories.length];

        for (int i = 0; i < categories.length; i++) {
            categoriesString[i] = categories[i].getName();
            categoriesInt[i] = booksRepo.findAllByDescription_Category(categories[i]).size();
        }

        model.addAttribute("categoriesString", categoriesString);
        model.addAttribute("categoriesInt", categoriesInt);


        return "stats";
    }
}
