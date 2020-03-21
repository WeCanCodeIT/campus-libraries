package org.wcci.library.controllers;

import org.springframework.web.bind.annotation.*;
import org.wcci.library.model.Book;
import org.wcci.library.model.Campus;
import org.wcci.library.storage.BookStorage;

import java.util.Collection;

@RestController
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

    @Override
    public Book add(@RequestBody Book element) {
        return bookStorage.store(element);
    }

    @Override
    public Collection<Book> remove(Long id) {
        bookStorage.delete(id);
        return bookStorage.fetchAll();
    }
    @GetMapping("/{id}/campus/")
    public Campus findBookCampus(@PathVariable Long id){
        return bookStorage.fetchById(id).getCampus();
    }
}
