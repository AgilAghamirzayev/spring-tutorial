package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/re")
public class RedirectController {
    /**
     * redirect
     * http://localhost:8088/re/a
     */

    @GetMapping("a")
    public String handle_get2() {
        return "redirect:/book";
    }


    /**
     * redirect
     * http://localhost:8088/re/b
     */

    @GetMapping("b")
    public RedirectView handle_get3() {
        return new RedirectView("/fw");
    }
}
