package app.controller;

import app.entity.Person;
import app.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PersonViewController {
    private final PersonService personService;

    public PersonViewController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/view/p")
    public String handle(Model model) {
        List<Person> data = personService.all();
        model.addAttribute("persons", data);
        return "all_users";
    }
}
