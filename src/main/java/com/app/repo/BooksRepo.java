package com.app.repo;

import com.app.model.Books;
import com.app.model.enums.Category;
import com.app.model.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepo extends JpaRepository<Books, Long> {
    List<Books> findAllByDescription_Genre(Genre genre);

    List<Books> findAllByDescription_Category(Category category);

    List<Books> findAllByNameContaining(String name);

    List<Books> findAllByNameContainingAndDescription_GenreAndDescription_Category(String name, Genre genre, Category category);
}
