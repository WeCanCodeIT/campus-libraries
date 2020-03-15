package org.wcci.library.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.library.model.Author;
import org.wcci.library.model.Campus;
import org.wcci.library.storage.repositories.AuthorRepository;
import org.wcci.library.storage.repositories.CampusRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class CampusStorageTest {

    private CampusRepository campusRepo;
    private CampusStorage underTest;
    private Campus testCampus;

    @BeforeEach
    void setUp() {
        campusRepo = mock(CampusRepository.class);
        underTest = new CampusStorageJpaImpl(campusRepo);
        testCampus = new Campus("Test Town");
    }

    @Test
    public void shouldSaveCampus() {
        underTest.store(testCampus);
        verify(campusRepo).save(testCampus);
    }

    @Test
    public void shouldReturnAllCampuses() {
        when(campusRepo.findAll()).thenReturn(Collections.singletonList(testCampus));
        Collection<Campus> result = underTest.fetchAll();
        assertThat(result).containsOnly(testCampus);
    }

    @Test
    public void shouldReturnASpecificCampus() {
        when(campusRepo.findById(1L)).thenReturn(Optional.of(testCampus));
        Campus result = underTest.fetchById(1L);
        assertThat(result).isEqualTo(testCampus);
    }

}
