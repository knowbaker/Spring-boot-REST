package com.singh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.singh.entity.Recipe;

public interface RecipeRespository extends JpaRepository<Recipe, Long> {

}
