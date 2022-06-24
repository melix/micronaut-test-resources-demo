package demo;

import io.micronaut.context.annotation.Value;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class DemoTest {

    @Inject
    BookRepository bookRepository;

    @Value("${my.test.property}")
    String myTestProperty;

    @Test
    @DisplayName("A custom test resource resolver can be used to resolve properties")
    void testCustomProperty() {
        assertEquals("my-test-value", myTestProperty);
    }

    @Test
    @DisplayName("A MySQL test container is required to run this test")
    void testItWorks() {
        Book book = new Book();
        book.setTitle("Yet Another Book");
        Book saved = bookRepository.save(book);
        assertNotNull(saved.getId());
        List<Book> books = bookRepository.findAll();
        assertEquals(3, books.size());
    }

}
