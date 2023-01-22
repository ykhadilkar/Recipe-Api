package com.example.recipeapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecipeApiApplicationTests {
	
	@Autowired
    private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
    void shouldExposeOpenAPIEndpoint() {

        var responseEntity = restTemplate.getForEntity("/api-docs", String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
