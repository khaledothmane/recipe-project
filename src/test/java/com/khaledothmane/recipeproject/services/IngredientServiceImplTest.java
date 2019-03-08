package com.khaledothmane.recipeproject.services;

import com.khaledothmane.recipeproject.commands.IngredientCommand;
import com.khaledothmane.recipeproject.converters.IngredientCommandToIngredient;
import com.khaledothmane.recipeproject.converters.IngredientToIngredientCommand;
import com.khaledothmane.recipeproject.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.khaledothmane.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.khaledothmane.recipeproject.model.Ingredient;
import com.khaledothmane.recipeproject.model.Recipe;
import com.khaledothmane.recipeproject.repositories.IngredientRepository;
import com.khaledothmane.recipeproject.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;
    IngredientService ingredientService;
    @Mock
    IngredientRepository ingredientRepository;

    private final IngredientToIngredientCommand converter = new IngredientToIngredientCommand(
            new UnitOfMeasureToUnitOfMeasureCommand());
    private final IngredientCommandToIngredient _converter = new IngredientCommandToIngredient(
            new UnitOfMeasureCommandToUnitOfMeasure());
    private final Long RECIPE_ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(this.recipeRepository, this.converter, this._converter, this.ingredientRepository);
    }

    @Test
    public void getIngredientByRecipeIdAndIngredientId() {
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        recipe.addingredient(ingredient1);
        recipe.addingredient(ingredient2);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        IngredientCommand ingredientCommand = ingredientService.getIngredientByRecipeIdAndIngredientId(1L, 2L);

        assertEquals(Long.valueOf(2L), ingredientCommand.getId());
        assertEquals(RECIPE_ID, ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());

    }
}