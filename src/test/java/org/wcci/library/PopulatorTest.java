package org.wcci.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.wcci.library.storage.AuthorStorage;
import org.wcci.library.storage.BookStorage;
import org.wcci.library.storage.CampusStorage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DirtiesContext
@SpringBootTest
public class PopulatorTest {

    @Autowired
    AuthorStorage authorStorage;
    @Autowired
    BookStorage bookStorage;
    @Autowired
    CampusStorage campusStorage;

    @Test
    public void shouldPopulateAuthorsCampusesBooks() throws Exception {

        assertAll(
                () -> assertThat(authorStorage.fetchAll()).hasSize(7),
                () -> assertThat(campusStorage.fetchAll()).hasSize(2),
                () -> assertThat(bookStorage.fetchAll()).hasSize(7)
        );
    }


}
