package demo;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class DemoTest {

    @Inject
    BookRepository bookRepository;

    @Inject
    AuthorRepository authorRepository;

    @Test
    @DisplayName("A MySQL test container is required to run this test")
    void testItWorks() {
        var author = authorRepository.save(new Author(null, "Cédric Champeau"));
        var book = new Book(null, "Yet Another Book " + UUID.randomUUID(), author);
        Book saved = bookRepository.save(book);
        assertNotNull(saved.id());
        List<Book> books = bookRepository.findAll();
        assertEquals(2, books.size());
        var cedric = authorRepository.findByNameIgnoreCase("Cédric CHAMPEAU");
        assertNotNull(cedric);
    }

}
