package org.wcci.library.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.wcci.library.model.Author;
import org.wcci.library.storage.AuthorStorage;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthorControllerTest {
    private AuthorStorage authorStorage;
    private AuthorController underTest;
    private Author testAuthor;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        authorStorage = mock(AuthorStorage.class);
        underTest = new AuthorControllerImpl(authorStorage);
        testAuthor = new Author("Joe", "Testa");
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
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
        mockMvc.perform(get("/api/authors/"))
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
        mockMvc.perform(get("/api/authors/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("Joe")))
                .andExpect(jsonPath("$.lastName", is("Testa")));
    }

    @Test
    public void createNewShouldPostNewAuthor() throws JsonProcessingException {
        Author newAuthor = new Author("Samantha", "Testin");
        underTest.createNew(newAuthor);
        verify(authorStorage).store(newAuthor);
    }

    @Test
    public void createNewShouldReturnCreatedAuthor() {
        Author newAuthor = new Author("Samantha", "Testin");
        Author result = underTest.createNew(newAuthor);
        assertThat(result).isEqualTo(newAuthor);
    }

    @Test
    public void shouldPostNewAuthorToApi() throws Exception {
        Author newAuthor = new Author("Samantha", "Testin");
        String newAuthorJson = objectMapper.writeValueAsString(newAuthor);
        mockMvc.perform(post("/api/authors/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newAuthorJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(newAuthorJson));
    }
}
