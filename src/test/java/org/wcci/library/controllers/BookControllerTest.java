package org.wcci.library.controllers;

import org.junit.jupiter.api.Test;
import org.wcci.library.model.Book;
import org.wcci.library.storage.BookStorage;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookControllerTest {
    @Test
    public void shouldReturnAllBooks(){
        BookStorage bookStorage = mock(BookStorage.class);
        BookController underTest = new BookControllerImpl(bookStorage);
        Book testBook = mock(Book.class);
        when(bookStorage.fetchAll()).thenReturn(Collections.singletonList(testBook));
        Collection<Book> result = underTest.retrieveAll();
        assertThat(result).containsOnly(testBook);
    }
}
