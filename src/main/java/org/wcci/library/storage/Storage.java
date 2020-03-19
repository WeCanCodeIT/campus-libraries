package org.wcci.library.storage;

import java.util.Collection;

public interface Storage<T> {
    T store(T item);

    Collection<T> fetchAll();

    T fetchById(long id);

    Collection<T> delete(Long id);
}
