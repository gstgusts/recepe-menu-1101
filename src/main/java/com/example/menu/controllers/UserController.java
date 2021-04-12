package com.example.menu.controllers;

import com.example.menu.data.Recipe;
import com.example.menu.data.UserRepository;
import com.example.menu.viewmodels.RecipeViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @GetMapping("")
    public String getIndex(Model model) {

        var repo = new UserRepository();

        var user = repo.getById(5);

        var allRecepies = repo.getRecepies();
        var userRecepies = user.getRecipes();

        var recepieResult = RecipeViewModel.map(allRecepies, userRecepies);

        model.addAttribute("user", user);
        model.addAttribute("recepies", recepieResult);

        return "index";
    }

    @GetMapping("/add")
    public ModelAndView addRecepie(@RequestParam int recId, @RequestParam int userId) {

        var repo = new UserRepository();

        var user = repo.getById(userId);

        var rec = repo.getRecipeById(recId);

        user.getRecipes().add(rec);

        repo.update(user);

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/remove")
    public ModelAndView removeRecepie(@RequestParam int recId, @RequestParam int userId) {

        var repo = new UserRepository();

        var user = repo.getById(userId);

        Recipe recipe = null;

        for (var rec :
                user.getRecipes()) {
            if(rec.getId() == recId) {
                recipe = rec;
                break;
            }
        }

        user.getRecipes().remove(recipe);

        repo.update(user);

        return new ModelAndView("redirect:/");
    }
}
