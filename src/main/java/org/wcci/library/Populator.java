package org.wcci.library;

import org.springframework.stereotype.Component;
import org.wcci.library.model.Author;
import org.wcci.library.model.Book;
import org.wcci.library.model.Campus;
import org.wcci.library.storage.AuthorStorage;
import org.wcci.library.storage.BookStorage;
import org.wcci.library.storage.CampusStorage;

@Component
public class Populator implements org.springframework.boot.CommandLineRunner {
    private final AuthorStorage authorStorage;
    private final BookStorage bookStorage;
    private final CampusStorage campusStorage;

    public Populator(AuthorStorage authorStorage, BookStorage bookStorage, CampusStorage campusStorage) {
        this.authorStorage = authorStorage;
        this.bookStorage = bookStorage;
        this.campusStorage = campusStorage;
    }

    @Override
    public void run(String... args) {
        Author kathy = new Author("Kathy", "Sierra");
        authorStorage.store(kathy);
        Author bert = new Author("Bert", "Bates");
        authorStorage.store(bert);
        Author ben = new Author("Benjamin", "Williams");
        authorStorage.store(ben);
        Author kent = new Author("Kent", "Beck");
        authorStorage.store(kent);
        Author martin = new Author("Martin", "Fowler");
        authorStorage.store(martin);
        Author micah = new Author("Micah", "Martin");
        authorStorage.store(micah);
        Author eric = new Author("Eric", "Freeman");
        authorStorage.store(eric);

        Campus columbus = new Campus("Columbus");
        campusStorage.store(columbus);
        Campus cleveland = new Campus("Cleveland");
        campusStorage.store(cleveland);

        Book headFirstJava = new Book("Head First Java", columbus, kathy, bert);
        bookStorage.store(headFirstJava);
        Book headFirstDesignPatterns = new Book("Head First Design Patterns", columbus, eric, bert);
        bookStorage.store(headFirstDesignPatterns);
        Book badAss = new Book("Badass: Making Users Awesome", columbus, kathy);
        bookStorage.store(badAss);
        Book tddByExample = new Book("Test Driven Development: By Example", columbus, kent);
        bookStorage.store(tddByExample);
        Book refactoring = new Book("Refactoring", columbus, martin);
        bookStorage.store(refactoring);
        Book apppc = new Book("Agile Principles, Patterns, and Practices in C#", cleveland, micah);
        bookStorage.store(apppc);
        Book headFirstDesignPatternsClev = new Book("Head First Design Patterns", cleveland, eric, bert);
        bookStorage.store(headFirstDesignPatternsClev);
    }

}
