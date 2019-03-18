package com.khaledothmane.recipeproject.api;

import com.khaledothmane.recipeproject.api.resources.RecipeResource;
import com.khaledothmane.recipeproject.api.resources.RecipeResourceAssembler;
import com.khaledothmane.recipeproject.commands.RecipeCommand;
import com.khaledothmane.recipeproject.converters.*;
import com.khaledothmane.recipeproject.model.Recipe;
import com.khaledothmane.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(
        path = "/api/recipe",
        produces = "application/json"
)
@CrossOrigin(origins = "*")
@Slf4j
public class RecipeRestController {

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand = new RecipeToRecipeCommand(
            new CategoryToCategoryCommand(),
            new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
            new NotesToNotesCommand()
    );
    private final RecipeCommandToRecipe recipeCommandToRecipe = new RecipeCommandToRecipe(
            new CategoryCommandToCategory(),
            new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
            new NotesCommandToNotes()
    );

    @Autowired
    public RecipeRestController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Resources<RecipeResource> getRecipes() {
        List<RecipeResource> recipeResources = new RecipeResourceAssembler().toResources(recipeRepository.findAll());
        Resources<RecipeResource> recipes = new Resources<>(recipeResources);
        recipes.add(
                linkTo(methodOn(RecipeRestController.class).getRecipes())
                .withRel("recipes")
        );
        return recipes;
        /*return StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
                .map(recipe -> recipeToRecipeCommand.convert(recipe))
                .collect(Collectors.toSet());*/
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Resource<RecipeResource> getRecipeByID(@PathVariable("id") String id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(Long.valueOf(id));
        if (recipeOptional.isPresent()) {
            RecipeResource recipeResource = new RecipeResourceAssembler().toResource(recipeOptional.get());
            Resource<RecipeResource> resource = new Resource<>(recipeResource);
            resource.add(
                    linkTo(methodOn(this.getClass()).getRecipeByID(id))
                    .withRel("recipe")
            );
            return resource;
        } else return null;

    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RecipeCommand> postRecipe(@RequestBody RecipeCommand recipeCommand) {
        Recipe recipeToSave = recipeCommandToRecipe.convert(recipeCommand);
        RecipeCommand toReturn = null;
        if (recipeToSave != null) {
            toReturn = recipeToRecipeCommand.convert(recipeRepository.save(recipeToSave));
            return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(toReturn, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RecipeCommand> putRecipe(@RequestBody RecipeCommand recipeCommand) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeCommand.getId());
        RecipeCommand returnedRecipe = null;
        if (recipeOptional.isPresent()) {
            Recipe savedRecipe = recipeRepository.save(recipeCommandToRecipe.convert(recipeCommand));
            returnedRecipe = recipeToRecipeCommand.convert(savedRecipe);
            return new ResponseEntity<>(returnedRecipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(returnedRecipe, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{recipe_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable("recipe_id") String recipe_id) {
        try {
            recipeRepository.deleteById(Long.valueOf(recipe_id));
        } catch (EmptyResultDataAccessException e) {
        }
    }

}
