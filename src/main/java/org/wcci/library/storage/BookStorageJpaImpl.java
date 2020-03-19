package org.wcci.library.storage;

import org.springframework.stereotype.Service;
import org.wcci.library.model.Book;
import org.wcci.library.storage.repositories.BookRepository;

import java.util.Collection;

@Service
public class BookStorageJpaImpl implements BookStorage {
    private final BookRepository bookRepository;

    public BookStorageJpaImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book store(Book item) {
        bookRepository.save(item);
        return null;
    }

    @Override
    public Collection<Book> fetchAll() {
        return (Collection<Book>) bookRepository.findAll();
    }

    @Override
    public Book fetchById(long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Collection<Book> delete(Long id) {
        bookRepository.delete(fetchById(id));
        return fetchAll();
    }
}
