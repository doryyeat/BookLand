package com.app.model;

import com.app.model.enums.Category;
import com.app.model.enums.Genre;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BooksDescription implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private int quantity;
    private String author;

    @Column(length = 5000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    public BooksDescription(Category category, Genre genre, int quantity, String description, String author) {
        this.category = category;
        this.genre = genre;
        this.quantity = quantity;
        this.description = description;
        this.author = author;
    }
}