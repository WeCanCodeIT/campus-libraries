package org.wcci.library.storage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wcci.library.model.Author;
import org.wcci.library.storage.repositories.AuthorRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AuthorStorageTest {
    @Test
    public void shouldSaveAuthor(){
        AuthorRepository authorRepo = mock(AuthorRepository.class);
        AuthorStorage underTest = new AuthorStorage(authorRepo);
        Author testAuthor = new Author("Tester", "O'Tested");
        underTest.store(testAuthor);
        verify(authorRepo).save(testAuthor);
    }

}
