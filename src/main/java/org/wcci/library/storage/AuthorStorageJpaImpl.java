package org.wcci.library.storage;

import org.wcci.library.model.Author;
import org.wcci.library.storage.repositories.AuthorRepository;

import java.util.Collection;

public class AuthorStorageJpaImpl implements AuthorStorage {
    private final AuthorRepository authorRepo;

    public AuthorStorageJpaImpl(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public void store(Author author) {
        authorRepo.save(author);
    }

    @Override
    public Collection<Author> fetchAll() {
        return (Collection<Author>) authorRepo.findAll();
    }

    @Override
    public Author fetchById(long id) {
        return authorRepo.findById(id).get();
    }
}
