package com.example.recipeapi.repository;

import com.example.recipeapi.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
