package com.singh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.singh.entity.Recipe;
import com.singh.repository.RecipeRespository;

@RestController
@RequestMapping(value="/recipes")
public class RecipeController {
	@Autowired
	private RecipeRespository repository;

	@GetMapping(value="/{recipeId}")
	public Recipe getRecipe(@PathVariable long recipeId) {
		return repository.findOne(recipeId);
	}
	
	@PostMapping
	public Recipe addRecipe(@RequestBody Recipe recipe) {
		return repository.save(recipe);
	}
	
	@PutMapping
	public Recipe updateRecipe(@RequestBody Recipe recipe) {
		return repository.save(recipe);
	}
	
	@PatchMapping
	public Recipe patchRecipe(@RequestBody Recipe recipe) {
		throw new UnsupportedOperationException("Use PUT for now - method under construction.");
	}
}
