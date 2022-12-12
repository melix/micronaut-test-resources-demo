package demo;

import io.micronaut.context.ApplicationContextBuilder;
import io.micronaut.context.ApplicationContextConfigurer;
import io.micronaut.context.annotation.ContextConfigurer;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class Application {

    @Inject
    private UserRepository userRepository;

    @Inject
    private BookRepository bookRepository;

    @Inject
    private AuthorRepository authorRepository;

    @ContextConfigurer
    public static class Configurer implements ApplicationContextConfigurer {
        @Override
        public void configure(ApplicationContextBuilder builder) {
            builder.deduceEnvironment(false);
            builder.banner(true);
        }
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

    @EventListener
    public void onStart(StartupEvent event) {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setName("Administrator");
            userRepository.save(admin);
        }
        if (bookRepository.count() == 0) {
            Author author = authorRepository.save(new Author(null, "Cédric Champeau"));
            Book book = new Book(null, "The Hitchhiker's Guide to the Galaxy", author);
            bookRepository.save(book);
        }
        if (System.getProperty("interrupt.on.startup") != null) {
            System.exit(0);
        }
    }
}
