package com.example.recipeapi.controller;

import com.example.recipeapi.model.Recipe;
import com.example.recipeapi.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/recipes")
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("")
    List<Recipe> getRecipes(){
        return (List<Recipe>) recipeRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Recipe> getRecipe(@PathVariable Long id){
        return recipeRepository.findById(id);
    }

    @PostMapping("")
    Recipe createRecipe(@RequestBody Recipe recipe){
        return recipeRepository.save(recipe);
    }

    @PutMapping("/{id}")
    Recipe updateRecipe(@RequestBody Recipe newRecipe, @PathVariable Long id){
        return recipeRepository.findById(id)
                .map(recipe -> {
                    recipe.setName(newRecipe.getName());
                    recipe.setDescription(newRecipe.getDescription());
                    return recipeRepository.save(recipe);
                })
                .orElseGet(() -> {
                    newRecipe.setId(id);
                    return recipeRepository.save(newRecipe);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id){
        recipeRepository.deleteById(id);
    }

}
