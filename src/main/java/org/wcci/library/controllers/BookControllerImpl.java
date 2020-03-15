package org.wcci.library.controllers;

import org.wcci.library.model.Book;
import org.wcci.library.storage.BookStorage;

import java.util.Collection;

public class BookControllerImpl implements BookController {
    private final BookStorage bookStorage;

    public BookControllerImpl(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
    }

    @Override
    public Collection<Book> retrieveAll() {
        return bookStorage.fetchAll();
    }
}
