package com.khaledothmane.recipeproject.repositories;

import com.khaledothmane.recipeproject.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
