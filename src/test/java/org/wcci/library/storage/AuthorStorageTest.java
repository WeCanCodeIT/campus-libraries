package org.wcci.library.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.library.model.Author;
import org.wcci.library.storage.repositories.AuthorRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AuthorStorageTest {

    private AuthorRepository authorRepo;
    private AuthorStorage underTest;
    private Author testAuthor;

    @BeforeEach
    void setUp() {
        authorRepo = mock(AuthorRepository.class);
        underTest = new AuthorStorageJpaImpl(authorRepo);
        testAuthor = new Author("Tester", "O'Tested");
    }

    @Test
    public void shouldSaveAuthor(){
        underTest.store(testAuthor);
        verify(authorRepo).save(testAuthor);
    }
    @Test
    public void shouldReturnAllAuthors(){
        when(authorRepo.findAll()).thenReturn(Collections.singletonList(testAuthor));
        Collection<Author> result = underTest.fetchAll();
        assertThat(result).containsOnly(testAuthor);
    }
    @Test
    public void shouldReturnASpecificAuthor(){
        when(authorRepo.findById(1L)).thenReturn(Optional.of(testAuthor));
        Author result = underTest.fetchById(1L);
        assertThat(result).isEqualTo(testAuthor);
    }

}
