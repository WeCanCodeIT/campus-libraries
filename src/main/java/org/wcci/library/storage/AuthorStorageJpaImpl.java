package org.wcci.library.storage;

import org.springframework.stereotype.Service;
import org.wcci.library.model.Author;
import org.wcci.library.model.Campus;
import org.wcci.library.storage.repositories.AuthorRepository;

import java.util.Collection;

@Service
public class AuthorStorageJpaImpl implements AuthorStorage {
    private final AuthorRepository authorRepo;

    public AuthorStorageJpaImpl(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author store(Author author) {
        authorRepo.save(author);
        return null;
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
