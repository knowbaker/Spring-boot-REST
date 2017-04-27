package com.singh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.singh.entity.Recipe;
import com.singh.repository.RecipeRespository;

@RestController
@RequestMapping(value="/recipes")
public class RecipeController {
	@Autowired
	private RecipeRespository repository;

	@RequestMapping(value="/{recipeId}", method=RequestMethod.GET)
	public Recipe getRecipe(@PathVariable long recipeId) {
		return repository.findOne(recipeId);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public long addRecipe(@RequestBody Recipe recipe) {
		return repository.save(recipe).getId();
	}
}
