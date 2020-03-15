package org.wcci.library.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.wcci.library.model.Author;
import org.wcci.library.storage.AuthorStorage;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthorControllerTest {
    private AuthorStorage authorStorage;
    private AuthorController underTest;
    private Author testAuthor;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        authorStorage = mock(AuthorStorage.class);
        underTest = new AuthorControllerImpl(authorStorage);
        testAuthor = new Author("Joe", "Testa");
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void shouldReturnAllAuthors() {
        when(authorStorage.fetchAll()).thenReturn(Collections.singletonList(testAuthor));
        Collection<Author> result = underTest.retrieveAll();
        assertThat(result).containsOnly(testAuthor);
    }

    @Test
    public void retrieveAllEndPointReturnsAllAuthors() throws Exception {
        when(authorStorage.fetchAll()).thenReturn(Collections.singletonList(testAuthor));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is("Joe")))
                .andExpect(jsonPath("$[0].lastName", is("Testa")));
    }

    @Test
    public void shouldReturnAuthorById() {
        when(authorStorage.fetchById(1L)).thenReturn(testAuthor);
        assertThat(underTest.retrieveById(1L)).isEqualTo(testAuthor);
    }

    @Test
    public void retrieveByIdEndpointFetchesAuthorById() throws Exception {
        when(authorStorage.fetchById(1L)).thenReturn(testAuthor);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("Joe")))
                .andExpect(jsonPath("$.lastName", is("Testa")));
    }
}
