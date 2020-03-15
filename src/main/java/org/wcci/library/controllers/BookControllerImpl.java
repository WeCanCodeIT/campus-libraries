package org.wcci.library.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.wcci.library.model.Book;
import org.wcci.library.storage.BookStorage;

import java.util.Collection;

@RequestMapping("/api/books")
public class BookControllerImpl implements BookController {
    private final BookStorage bookStorage;

    public BookControllerImpl(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
    }

    @Override
    public Collection<Book> retrieveAll() {
        return bookStorage.fetchAll();
    }

    @Override
    public Book retrieveById(Long id) {
        return bookStorage.fetchById(id);
    }
}
