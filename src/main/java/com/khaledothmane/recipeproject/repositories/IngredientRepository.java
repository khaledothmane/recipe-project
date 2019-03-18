package com.khaledothmane.recipeproject.repositories;

import com.khaledothmane.recipeproject.model.Ingredient;
import com.khaledothmane.recipeproject.model.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    List<Ingredient> findByRecipeId(Long recipeId);

}
