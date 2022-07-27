package demo;

import io.micronaut.context.annotation.Requires;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@MicronautTest
// only required because Maven doesn't support custom resolvers
@Requires(property = "my.user.name")
class GreeterTest {

    @Inject
    Greeter greeter;


    @Test
    @DisplayName("Says hello")
    void saysHello() {
        greeter.sayHello();
        Assertions.assertEquals("Hello, world!", greeter.getGreeting());
    }

}
