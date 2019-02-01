package com.khaledothmane.recipeproject.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

    private Long id;
    private String Description;
    private BigDecimal amount;
    private UnitOfMeasureCommand unitOfMeasureCommand;
}
