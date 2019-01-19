package com.khaledothmane.recipeproject.services;

import com.khaledothmane.recipeproject.model.Recipe;
import com.khaledothmane.recipeproject.repositories.RecipeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        // Asking mockito to give us the mocks (instance of RecipeRepository)
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("After");
        this.recipeService = null;
    }

    @Test
    public void getRecipesTest() {

        HashSet<Recipe> recipes2 = new HashSet<>();
        recipes2.add(new Recipe());
        when(recipeService.getRecipes()).thenReturn(recipes2);

        /**
         * It's normal to get 0 size of recipes, while the persistence is bootstrap along with
         * Spring context which we are not using yet.
         * forcing getRecipes() to return size 1.
         */
        Set<Recipe> recipes = recipeService.getRecipes(); // returns 0

        verify(recipeRepository, times(1)).findAll();

        assertEquals(recipes.size(), 1);
    }

}