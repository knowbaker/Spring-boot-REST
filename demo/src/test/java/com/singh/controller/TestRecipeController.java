package com.singh.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import org.assertj.core.api.Condition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.singh.entity.Recipe;
import com.singh.repository.RecipeRespository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT )
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestRecipeController {
	@LocalServerPort
	private int port;
	@Value("${security.user.name}")
	private String user;
	@Value("${security.user.password}")
	private String pass;
	@Autowired
	private TestRestTemplate rest;
	@Autowired
	RecipeRespository repo;

	@Test
	public void test() throws URISyntaxException {
		Recipe recipe = new Recipe();
		recipe.setName("Recipe1");
		recipe.setDesc("First Recipe");
		long recipeId = repo.save(recipe).getId();
		
		URI uri = new URI("/recipes/"+recipeId);
		RequestEntity<Void> requestEntity = RequestEntity.get(uri).build();
		ResponseEntity<Recipe> responseEntity = rest.withBasicAuth(user, pass).exchange(requestEntity, Recipe.class);
		
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		Recipe responseRecipe = responseEntity.getBody();
		assertThat(responseRecipe).isNotNull();
		assertThat(responseRecipe).hasFieldOrPropertyWithValue("name", recipe.getName());
		assertThat(responseRecipe).hasFieldOrPropertyWithValue("desc", recipe.getDesc());
		assertThat(responseRecipe).doesNotHave(new Condition<Recipe>() {
			@Override
			public boolean matches(Recipe value) {
				//TODO: Should I check value.getId()?
				return value.getName() == null || value.getDesc() == null;
			}
		});
	}
}
