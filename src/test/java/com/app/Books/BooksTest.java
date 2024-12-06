package com.app.Books;

import com.app.model.Books;
import com.app.repo.BooksRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BooksTest {

    @Autowired
    protected BooksRepo booksRepo;

    @Test
    void testBookFindByIdSuccess() {
        Books book = booksRepo.save(new Books("name", "isbn", "date", new String[]{"imageUrl1", "imageUrl2"}));

        Books find = booksRepo.findById(book.getId()).orElse(new Books());

        assertEquals(find.getName(), book.getName());
        assertEquals(find.getIsbn(), book.getIsbn());
        assertEquals(find.getDate(), book.getDate());

        booksRepo.deleteById(find.getId());
    }

    @Test
    void testBookFindByIdNotFound() {
        assertNull(booksRepo.findById(0L).orElse(null));
    }

    @Test
    void testBookAddSuccess() {
        Books save = new Books("name", "isbn", "date", new String[]{"imageUrl1", "imageUrl2"});

        Books saved = booksRepo.save(save);

        assertEquals(saved.getName(), save.getName());
        assertEquals(saved.getIsbn(), save.getIsbn());
        assertEquals(saved.getDate(), save.getDate());

        booksRepo.deleteById(saved.getId());
    }

    @Test
    void testBookUpdateSuccess() {
        Books old = booksRepo.save(new Books("name", "isbn", "date", new String[]{"imageUrl1", "imageUrl2"}));

        Books update = new Books("new name", "new isbn", "new date", new String[]{"new imageUrl1", "new imageUrl2"});

        old.update(update);

        Books updated = booksRepo.save(old);

        assertEquals(updated.getName(), update.getName());
        assertEquals(updated.getIsbn(), update.getIsbn());
        assertEquals(updated.getDate(), update.getDate());

        booksRepo.deleteById(updated.getId());
    }

    @Test
    void testBookDeleteByIdSuccess() {
        Books book = booksRepo.save(new Books("name", "isbn", "date", new String[]{"imageUrl1", "imageUrl2"}));

        Long bookId = book.getId();

        assertNotNull(booksRepo.findById(bookId).orElse(null));

        booksRepo.deleteById(bookId);

        assertNull(booksRepo.findById(bookId).orElse(null));
    }
}
