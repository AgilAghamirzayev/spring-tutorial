package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fw")
public class ForwardController {

    /**
     * redirect
     * http://localhost:8088/fw
     */

    @GetMapping
    public String handle_b() {
        return "forward:/book";
    }
}
