package org.wcci.library.controllers;

import org.wcci.library.model.Book;

import java.util.Collection;

public interface BookController {
    Collection<Book> retrieveAll();
}
