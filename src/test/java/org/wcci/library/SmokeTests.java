package org.wcci.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.wcci.library.controllers.BookController;

import static org.assertj.core.api.Assertions.assertThat;
@DirtiesContext
@SpringBootTest
class SmokeTests {
    @Autowired
    BookController bookController;
    @Test
    void contextLoads() {
        assertThat(bookController).isNotNull();
    }

}
