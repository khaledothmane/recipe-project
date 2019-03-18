package com.khaledothmane.recipeproject.api.resources;

import com.khaledothmane.recipeproject.api.IngredientRestController;
import com.khaledothmane.recipeproject.model.Ingredient;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class IngredientResourceAssembler extends ResourceAssemblerSupport<Ingredient, IngredientResource> {

    public IngredientResourceAssembler() {
        super(IngredientRestController.class, IngredientResource.class);
    }

    @Override
    protected IngredientResource instantiateResource(Ingredient ingredient) {
        return new IngredientResource(ingredient);
    }

    @Override
    public IngredientResource toResource(Ingredient ingredient) {
        return super.createResourceWithId(ingredient.getId(), ingredient);
    }
}
