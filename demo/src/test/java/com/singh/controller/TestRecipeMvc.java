package com.singh.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.singh.entity.Recipe;
import com.singh.repository.RecipeRespository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestRecipeMvc {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private RecipeRespository repo;
	
	@Test
	@WithMockUser
	public void testGet() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setName("R1");
		recipe.setDesc("First Recipe");
		long recipeId = repo.save(recipe).getId();
		
		mvc.perform(get("/recipes/"+recipeId))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
}
