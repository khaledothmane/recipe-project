package com.khaledothmane.recipeproject.repositories;

import com.khaledothmane.recipeproject.model.Notes;
import org.springframework.data.repository.CrudRepository;

public interface NotesRepository extends CrudRepository<Notes, Long> {
}
