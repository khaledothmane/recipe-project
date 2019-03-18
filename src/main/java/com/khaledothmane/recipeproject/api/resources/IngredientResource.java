package com.khaledothmane.recipeproject.api.resources;

import com.khaledothmane.recipeproject.model.Ingredient;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

public class IngredientResource extends ResourceSupport {

    @Getter
    private String description;

    @Getter
    private BigDecimal amount;

    @Getter
    private String unitOfMeasure;


    public IngredientResource(Ingredient ingredient) {
        this.description = ingredient.getDescription();
        this.amount = ingredient.getAmount();
        this.unitOfMeasure = ingredient.getUnitOfMeasure().getUom();
    }
}
