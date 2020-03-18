package org.wcci.library.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wcci.library.model.Author;
import org.wcci.library.storage.AuthorStorage;

import java.util.Collection;

@RestController
@RequestMapping("/api/authors")
public class AuthorControllerImpl implements AuthorController {
    private final AuthorStorage authorStorage;

    public AuthorControllerImpl(AuthorStorage authorStorage) {
        this.authorStorage = authorStorage;
    }

    @Override
    public Collection<Author> retrieveAll() {
        return authorStorage.fetchAll();
    }

    @Override
    public Author retrieveById(Long id) {
        return authorStorage.fetchById(id);
    }

    @Override
    public Author createNew(@RequestBody Author newResource) {
        authorStorage.store(newResource);
        return newResource;
    }

}
