package app.controller;

import app.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/book")
public class BookController {

    /**
     * main resource
     * http://localhost:8088/book
     */


    @GetMapping
    private Book handle_get1() {
        return new Book("Java", "Jim");
    }
}
