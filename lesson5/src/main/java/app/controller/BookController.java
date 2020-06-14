package app.controller;

import app.entity.Book;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("book")
public class BookController {

    @GetMapping
    public Book handle_get1() {
        return new Book("Java", "Jim");
    }

    @GetMapping("x")
    public Book handle_get2(@RequestParam String author) {
        return new Book("java", author);
    }

    @GetMapping("y")
    public Book handle_get3(HttpSession session) {
        final Object attribute = session.getAttribute("author");
        String author = (String) attribute;
        return new Book("Java", author);
    }

    @GetMapping("z")
    public Book handle_get4(Model model) {
        final Object attribute = model.getAttribute("author");
        String author = (String) attribute;
        return new Book("Java", author);
    }

    @GetMapping("w")
    public Book handle_get5(@ModelAttribute("author") String author) {
        return new Book("Java", author);
    }
}
