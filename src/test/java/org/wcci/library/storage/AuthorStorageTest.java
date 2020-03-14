package org.wcci.library.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wcci.library.model.Author;
import org.wcci.library.storage.repositories.AuthorRepository;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AuthorStorageTest {

    private AuthorRepository authorRepo;
    private AuthorStorage underTest;
    private Author testAuthor;

    @BeforeEach
    void setUp() {
        authorRepo = mock(AuthorRepository.class);
        underTest = new AuthorStorage(authorRepo);
        testAuthor = new Author("Tester", "O'Tested");
    }

    @Test
    public void shouldSaveAuthor(){
        underTest.store(testAuthor);
        verify(authorRepo).save(testAuthor);
    }
    @Test
    public void shouldReturnAllAuthors(){
        when(authorRepo.findAll()).thenReturn(Collections.EMPTY_LIST);
        Collection<Author> result = underTest.fetchAll();
        assertThat(result).isEmpty();
    }

}
