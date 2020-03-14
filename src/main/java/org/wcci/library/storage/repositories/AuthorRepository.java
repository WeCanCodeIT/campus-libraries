package org.wcci.library.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.library.model.Author;

public interface AuthorRepository extends CrudRepository<Author,Long> {
}
