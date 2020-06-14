package app.controller;

import app.form.FormData;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;

@Log4j2
@Controller
@RequestMapping("/form")
public class FormController {

    private static String ftm(String format, Object... args){
        return String.format(format, args);
    }

    @GetMapping
    public String handleGETa() {
        log.info("GET -> /form");
        return "form_a";
    }

    @PostMapping
    public String handlePOSTa(@RequestParam String x, @RequestParam String y){
        log.info(ftm("POST -> /form x=%s",x));
        log.info(ftm("POST -> /form y=%s",y));
        return "form_a";
    }

    @GetMapping("b")
    public String handleGETb(){
        log.info("GET -> /form/b");
        return "form_b";
    }

    @PostMapping("b")
    public String handlePOSTb(FormData form){
        log.info(ftm("POST -> /form/b form=%s", form));
        return "form_b";
    }

    @GetMapping("c")
    public String handleGETc(Model model){
        log.info("GET -> /form/c");
        model.addAttribute("handler","process_x");
        return "form_c";
    }

    @PostMapping("process_x")
    public String handlePOSTc(FormData data){
        log.info(ftm("POST -> /form/process_x form=%s",data));
        return "form_c";
    }
}
