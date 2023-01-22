package com.example.recipeapi.controller;

import com.example.recipeapi.model.Recipe;
import com.example.recipeapi.repository.RecipeRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@OpenAPIDefinition(
        info = @Info(
                title = "Recipe mgmt API",
                version = "1.0"),
        tags = @Tag(
                name = "Recipes REST API"))
@CrossOrigin
@RestController
@RequestMapping("api/v1/recipes")
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;

    @Operation(summary = "Get all recipes.", method = "GET", tags = "Recipe CRUD")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Recipes retrieved successfully."
        )
    })
    @Transactional(readOnly = true)
    @GetMapping("")
    List<Recipe> getRecipes(){
        return (List<Recipe>) recipeRepository.findAll();
    }

    @Operation(summary = "Get recipe", method = "GET", tags = "Recipe CRUD")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Recipe retrieved successfully."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Recipe not found."
            )
    })
    @GetMapping("/{id}")
    Optional<Recipe> getRecipe(@PathVariable Long id){
        return recipeRepository.findById(id);
    }

    @Operation(summary = "Saves provided recipe.", method = "POST", tags = "Recipe CRUD")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Recipe successfully saved.",
                    headers = @Header(
                            name = "Name",
                            description = "Name",
                            required = true,
                            schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Passed recipe is invalid."
            )
    })
    @PostMapping("")
    Recipe createRecipe(@RequestBody Recipe recipe){
        return recipeRepository.save(recipe);
    }

    @Operation(summary = "Update Recipe.", method = "PATCH", tags = "Recipe CRUD")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Recipe successfully saved."
            )
    })
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

    @Operation(summary = "Delete recipe.", method = "DELETE", tags = "Recipe CRUD")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Recipe successfully deleted."
            )
    })
    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id){
        recipeRepository.deleteById(id);
    }

}
