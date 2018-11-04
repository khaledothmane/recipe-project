package com.khaledothmane.recipeproject.controllers;

import com.khaledothmane.recipeproject.model.Category;
import com.khaledothmane.recipeproject.model.UnitOfMeasure;
import com.khaledothmane.recipeproject.repositories.CategoryRepository;
import com.khaledothmane.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        Optional<Category> category = categoryRepository.findByName("American");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByUom("Piston");
        System.out.println("#### Unit of measue : " + unitOfMeasure.get());
        System.out.println("#### Category" + category.get());
        return "index";
    }

}
