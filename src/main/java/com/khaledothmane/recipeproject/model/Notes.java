package com.khaledothmane.recipeproject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(exclude = "recipe")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", recipeNotes='" + recipeNotes + '\'' +
                '}';
    }
}
