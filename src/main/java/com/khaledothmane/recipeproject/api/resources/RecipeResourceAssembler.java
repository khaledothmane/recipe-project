package com.khaledothmane.recipeproject.api.resources;

import com.khaledothmane.recipeproject.api.RecipeRestController;
import com.khaledothmane.recipeproject.model.Recipe;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class RecipeResourceAssembler extends ResourceAssemblerSupport<Recipe, RecipeResource> {

    public RecipeResourceAssembler() {
        super(RecipeRestController.class, RecipeResource.class);
    }

    @Override
    protected RecipeResource instantiateResource(Recipe entity) {
        return new RecipeResource(entity);
    }

    @Override
    public RecipeResource toResource(Recipe recipe) {
        return createResourceWithId(recipe.getId(), recipe);
    }
}
