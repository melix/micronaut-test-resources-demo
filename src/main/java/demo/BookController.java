/*
 * Copyright 2003-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package demo;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import java.util.List;
import java.util.Optional;

@Controller("/")
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Get("/books")
    public List<Book> list() {
        return bookRepository.findAll();
    }

    @Post("/books/add")
    public Book add(String title, String author) {
        Optional<Author> authorOpt = authorRepository.findByNameIgnoreCase(author);
        if (authorOpt.isEmpty()) {
            authorOpt = Optional.of(authorRepository.save(new Author(null, author)));
        }
        var book = new Book(null, title, authorOpt.get());
        return bookRepository.save(book);
    }

    @Get("/books/{id}")
    public Book get(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Delete("/books/{id}")
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Delete("/books/title/{title}")
    public void delete(String title) {
        bookRepository.deleteByTitle(title);
    }
}
