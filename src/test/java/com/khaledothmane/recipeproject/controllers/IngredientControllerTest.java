package com.khaledothmane.recipeproject.controllers;

import com.khaledothmane.recipeproject.commands.IngredientCommand;
import com.khaledothmane.recipeproject.services.IngredientService;
import com.khaledothmane.recipeproject.services.RecipeService;
import com.khaledothmane.recipeproject.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IngredientControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    IngredientService ingredientService;
    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController controller;
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void listIngredients() {

    }

    @Test
    public void showIngredientForm() throws Exception {
        // given
        IngredientCommand ingredientCommand = new IngredientCommand();

        // when
        when(ingredientService.getIngredientByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.setOfUom()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(get("/recipe/1/ingredient/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomSet"));
    }

    @Test
    public void saveOrUpdateIngredient() throws Exception {
        // given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);

        // when
        when(ingredientService.saveIngredientCommand(any())).thenReturn(ingredientCommand);

        // then
        mockMvc.perform(post("/recipe/1/ingredient")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("Description","")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/ingredient/1/show"));
    }
}