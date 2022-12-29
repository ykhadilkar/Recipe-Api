package com.example.recipeapi;

import com.example.recipeapi.model.Recipe;
import com.example.recipeapi.repository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(RecipeRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Recipe("Bilbo Baggins", "burglar")));
            log.info("Preloading " + repository.save(new Recipe("Frodo Baggins", "thief")));
        };
    }
}