package org.wcci.library.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.library.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
