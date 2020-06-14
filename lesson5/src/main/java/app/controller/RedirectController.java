package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/re")
public class RedirectController {

    /**
     * redirect
     * http://localhost:8081/re/a
     * to
     * http://localhost:8081/book
     */

    @GetMapping("a")
    public RedirectView handle_a() {
        return new RedirectView("/book");
    }

    /**
     * redirect
     * http://localhost:8081/re/b
     * to
     * http://localhost:8081/book?find=java
     */

    @GetMapping("b")
    public RedirectView handle_b(RedirectAttributes ra) {
        ra.addAttribute("find","java");
        return new RedirectView("/book");
    }

    /**
     * redirect
     * http://localhost:8081/re/c
     * to
     * http://localhost:8081/book/x?author=Alex
     *
     * you mustn't pass any sensitive data
     */

    @GetMapping("c")
    public RedirectView handle_c(RedirectAttributes ra) {
        ra.addAttribute("author","Aqil");
        return new RedirectView("/book/x");
    }

    /**
     * redirect
     * http://localhost:8081/re/d
     * to
     * http://localhost:8081/book/y
     */

    @GetMapping("d")
    public RedirectView handle_d(HttpSession session) {
        session.setAttribute("author", "AqilD");
        return new RedirectView("/book/y");
    }

    /**
     * redirect
     * http://localhost:8081/re/e
     * to
     * http://localhost:8081/book/y
     *
     */

    @GetMapping("e")
    public RedirectView handle_e(RedirectAttributes ra) {
        ra.addFlashAttribute("author", "AqilE");
        return new RedirectView("/book/z");
    }

    /**
     * redirect
     * http://localhost:8081/re/f
     * to
     * http://localhost:8081/book/w
     *
     */

    @GetMapping("f")
    public RedirectView handle_f(RedirectAttributes ra) {
        ra.addFlashAttribute("author", "AlexF");
        return new RedirectView("/book/w");
    }
}
