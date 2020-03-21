package org.wcci.library.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.wcci.library.model.Campus;
import org.wcci.library.storage.CampusStorage;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CampusControllerTest {

    private CampusStorage campusStorage;
    private CampusController underTest;
    private Campus testCampus;

    @BeforeEach
    void setUp() {
        campusStorage = mock(CampusStorage.class);
        underTest = new CampusControllerImpl(campusStorage);
        testCampus = new Campus("Test Town");
    }

    @Test
    public void shouldReturnAllCampuses() {
        when(campusStorage.fetchAll()).thenReturn(Collections.singletonList(testCampus));
        Collection<Campus> result = underTest.retrieveAll();
        assertThat(result).containsOnly(testCampus);
    }

    @Test
    public void fetchAllEndpointReturnsAllBooks() throws Exception {
        when(campusStorage.fetchAll()).thenReturn(Collections.singletonList(testCampus));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(get("/api/campuses/"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].location", is("Test Town")));

    }

    @Test
    public void retrieveByIdShouldReturnASpecificCampusById() {
        when(campusStorage.fetchById(1L)).thenReturn(testCampus);
        Campus result = underTest.retrieveById(1L);
        assertThat(result).isEqualTo(testCampus);
    }

    @Test
    public void fetchByIdEndpointReturnASpecificBook() throws Exception {
        when(campusStorage.fetchById(1L)).thenReturn(testCampus);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(get("/api/campuses/1/"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.location", is("Test Town")));
    }

    @Test
    public void addShouldAddTheGivenCampusToTheApi() {
        Campus newCampus = new Campus("Testerville");
        when(campusStorage.store(newCampus)).thenReturn(newCampus);
        Campus addedCampus = underTest.add(newCampus);
        assertThat(addedCampus).isEqualTo(newCampus);
    }

    @Test
    public void addEndpointReturnsTheNewCampus() throws Exception {
        Campus newCampus = new Campus("Testerville");
        when(campusStorage.store(newCampus)).thenReturn(newCampus);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String campusJson = mapper.writeValueAsString(newCampus);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(post("/api/campuses/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(campusJson))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.location", is("Testerville")));
    }
}
