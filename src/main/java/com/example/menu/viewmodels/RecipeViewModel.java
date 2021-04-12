package com.example.menu.viewmodels;

import com.example.menu.data.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeViewModel {
    private boolean isAssigned;
    private Recipe recipe;

    public static List<RecipeViewModel> map(List<Recipe> recepies, Set<Recipe> userRecepies) {
         List<RecipeViewModel> finalList = new ArrayList<>();

        for (var rec :
                recepies) {

            RecipeViewModel model = new RecipeViewModel(false, rec);

            if (userRecepies.stream().anyMatch(a -> a.getId() == rec.getId())) {
                model.isAssigned = true;
            }

            finalList.add(model);
        }

         return finalList;
    }
}
