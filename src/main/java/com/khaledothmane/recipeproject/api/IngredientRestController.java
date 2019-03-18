package com.khaledothmane.recipeproject.api;

import com.khaledothmane.recipeproject.api.resources.IngredientResource;
import com.khaledothmane.recipeproject.api.resources.IngredientResourceAssembler;
import com.khaledothmane.recipeproject.model.Ingredient;
import com.khaledothmane.recipeproject.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping(path = "/api/ingredient", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientRestController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientRestController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Resources<IngredientResource> getIngredients() {
        List<IngredientResource> ingredientResourceList = new IngredientResourceAssembler()
                .toResources(ingredientRepository.findAll());
        Resources<IngredientResource> ingredientResources = new Resources<>(ingredientResourceList);
        ingredientResources.add(
                linkTo(methodOn(this.getClass()).getIngredients())
                .withRel("ingredients")
        );
        return ingredientResources;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Resource<IngredientResource> getIngredientById(@PathVariable("id") String id) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(Long.valueOf(id));
        Resource<IngredientResource> ingredientResource = new Resource<>(
                new IngredientResourceAssembler().toResource(optionalIngredient.orElse(null))
        );
        ingredientResource.add(
                linkTo(methodOn(this.getClass()).getIngredientById(id))
                .withRel("ingredient")
        );
        return ingredientResource;
    }

}
