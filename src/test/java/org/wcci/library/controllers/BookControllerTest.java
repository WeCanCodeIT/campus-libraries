package org.wcci.library.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.wcci.library.model.Author;
import org.wcci.library.model.Book;
import org.wcci.library.model.Campus;
import org.wcci.library.storage.BookStorage;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookControllerTest {

    private BookStorage bookStorage;
    private BookController underTest;
    private Book testBook;

    @BeforeEach
    void setUp() {
        bookStorage = mock(BookStorage.class);
        underTest = new BookControllerImpl(bookStorage);
        testBook = new Book("Testing In Spring", new Campus("Test Town"), new Author("Joe", "Testa"));
    }

    @Test
    public void shouldReturnAllBooks() {
        when(bookStorage.fetchAll()).thenReturn(Collections.singletonList(testBook));
        Collection<Book> result = underTest.retrieveAll();
        assertThat(result).containsOnly(testBook);
    }

    @Test
    public void fetchAllEndpointReturnsAllBooks() throws Exception {
        when(bookStorage.fetchAll()).thenReturn(Collections.singletonList(testBook));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(get("/api/books/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Testing In Spring")))
                .andExpect(jsonPath("$[0].campus.location", is("Test Town")));

    }

    @Test
    public void shouldReturnASpecificBook() {
        when(bookStorage.fetchById(1L)).thenReturn(testBook);
        Book result = underTest.retrieveById(1L);
        assertThat(result).isEqualTo(testBook);
    }

    @Test
    public void fetchByIdEndpointReturnASpecificBook() throws Exception {
        when(bookStorage.fetchById(1L)).thenReturn(testBook);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("Testing In Spring")))
                .andExpect(jsonPath("$.campus.location", is("Test Town")));

    }
}
