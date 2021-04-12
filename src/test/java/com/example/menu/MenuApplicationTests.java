package com.example.menu;

import com.example.menu.data.Recipe;
import com.example.menu.data.User;
import com.example.menu.data.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class MenuApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void getRecepies() {
        var repo = new UserRepository();
        var result = repo.getRecepies();
        System.out.println(result.size());
    }

    @Test
    void getUser() {
        var repo = new UserRepository();
        var result = repo.getById(1);
        System.out.println(result.getLogin());

        for (var rec :
                result.getRecipes()) {
            System.out.println(rec.getTitle());
        }
    }

    @Test
    void saveSimpleUser() {
        var repo = new UserRepository();

        var user = new User(0, "Gusts2", null);

        repo.save(user);
    }

    @Test
    void saveComplexUser() {
        var repo = new UserRepository();

        var user = new User(0, "Gusts3", null);

        var rec1 = new Recipe(1, "Test title 2");
        var rec2 = new Recipe(2, "Test title 3");

        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(rec1);
        recipeSet.add(rec2);

        user.setRecipes(recipeSet);

        repo.save(user);
    }
}
