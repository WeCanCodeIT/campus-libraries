package org.wcci.library.storage;

import org.wcci.library.model.Author;
import org.wcci.library.storage.repositories.AuthorRepository;

import java.util.Collection;

public class AuthorStorage {
    private final AuthorRepository authorRepo;

    public AuthorStorage(AuthorRepository authorRepo) {

        this.authorRepo = authorRepo;
    }

    public void store(Author author) {
        authorRepo.save(author);
    }

    public Collection<Author> fetchAll() {
        return (Collection<Author>) authorRepo.findAll();
    }
}
