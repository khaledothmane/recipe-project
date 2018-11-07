package com.khaledothmane.recipeproject.repositories;

import com.khaledothmane.recipeproject.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    public Optional<Category> findByName(String name);

    @Override
    Iterable<Category> findAll();
}
