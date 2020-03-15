package org.wcci.library.storage;

import org.wcci.library.model.Author;

import java.util.Collection;

public interface Storage<T> {
    void store(T item);

    Collection<Author> fetchAll();

    T fetchById(long id);
}
