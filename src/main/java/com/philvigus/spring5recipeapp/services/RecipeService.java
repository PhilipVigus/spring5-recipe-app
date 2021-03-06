package com.philvigus.spring5recipeapp.services;

import com.philvigus.spring5recipeapp.commands.RecipeCommand;
import com.philvigus.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
