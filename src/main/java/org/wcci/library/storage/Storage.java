package org.wcci.library.storage;

import java.util.Collection;

public interface Storage<T> {
    void store(T item);

    Collection<T> fetchAll();

    T fetchById(long id);
}
