package org.wcci.library.storage;

import org.wcci.library.model.Author;

import java.util.Collection;

public interface AuthorStorage {
    void store(Author author);

    Collection<Author> fetchAll();

    Author fetchById(long id);
}
