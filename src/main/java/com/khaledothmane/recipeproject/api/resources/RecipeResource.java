package com.khaledothmane.recipeproject.api.resources;

import com.khaledothmane.recipeproject.model.Recipe;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class RecipeResource extends ResourceSupport {

    @Getter
    private String description;

    @Getter
    private Integer prepTime;

    @Getter
    private Integer cookTime;

    @Getter
    private Integer servings;

    @Getter
    private String source;

    @Getter
    private String url;

    @Getter
    private String directions;

    @Getter
    private List<IngredientResource> ingredientResources;

    public RecipeResource(Recipe recipe) {
        this.description = recipe.getDescription();
        this.prepTime = recipe.getPrepTime();
        this.cookTime = recipe.getCookTime();
        this.servings = recipe.getServings();
        this.source = recipe.getSource();
        this.url = recipe.getUrl();
        this.directions = recipe.getDirections();
        this.ingredientResources = new IngredientResourceAssembler().toResources(
                recipe.getIngredients()
        );
    }
}
