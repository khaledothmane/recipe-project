package com.khaledothmane.recipeproject.services;

import com.khaledothmane.recipeproject.commands.UnitOfMeasureCommand;
import com.khaledothmane.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.khaledothmane.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository repository;
    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository repository, UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Set<UnitOfMeasureCommand> setOfUom() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(converter::convert).collect(Collectors.toSet());
    }
}
