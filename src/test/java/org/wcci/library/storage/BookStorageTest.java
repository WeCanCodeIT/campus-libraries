package org.wcci.library.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.library.model.Author;
import org.wcci.library.model.Book;
import org.wcci.library.model.Campus;
import org.wcci.library.storage.repositories.BookRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BookStorageTest {

    private BookRepository bookRepo;
    private BookStorage underTest;
    private Book testBook;

    @BeforeEach
    void setUp() {
        bookRepo = mock(BookRepository.class);
        underTest = new BookStorageJpaImpl(bookRepo);
        testBook = new Book("Testing In Spring", new Campus("Testing Town"), new Author("Joe", "Testa"));
    }

    @Test
    public void shouldSaveAuthor() {
        underTest.store(testBook);
        verify(bookRepo).save(testBook);
    }

    @Test
    public void shouldReturnAllAuthors() {
        when(bookRepo.findAll()).thenReturn(Collections.singletonList(testBook));
        Collection<Book> result = underTest.fetchAll();
        assertThat(result).containsOnly(testBook);
    }

    @Test
    public void shouldReturnASpecificAuthor() {
        when(bookRepo.findById(1L)).thenReturn(Optional.of(testBook));
        Book result = underTest.fetchById(1L);
        assertThat(result).isEqualTo(testBook);
    }
}
