package app.controller;

import app.entity.Person;
import app.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data/p") // root
public class PersonDataController {

    private final PersonService personService;

    public PersonDataController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping
    public List<Person> handle(){
        return personService.all();
    }

    @RequestMapping("/upper")
    public List<Person> handle1(){
        return personService.all().stream().map(p ->
                new Person(p.getId(), p.getName().toUpperCase())).collect(Collectors.toList());
    }

    @RequestMapping("/lower")
    public List<Person> handle2(){
        return personService.all().stream()
                .map(p -> new Person(p.getId(),p.getName().toLowerCase()))
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Person> handle3(){
        return personService.all();
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Person> handle4(){
        return personService.all();
    }

    @GetMapping("/{id}")
    public List<Person> handle5(@PathVariable("id") int i){
        return personService.all().stream().filter(p -> p.getId() == i).collect(Collectors.toList());
    }

    @GetMapping("/byid")
    public List<Person> handle6(@RequestParam("x") int id) {
        return personService.all().stream().filter(p -> p.getId() == id).collect(Collectors.toList());
    }


//
//    @GetMapping("/byidop")
//    public List<Person> handle7(@RequestParam("x") Optional<Integer> id){
//        return id
//                .map(pid -> personService.by(p -> p.getId() == pid))
//                .orElse(personService.all());
//    }

    @GetMapping("/byidop")
    public List<Person> handle8(@RequestParam Optional<Integer> id){
        return id
                .map(pid -> personService.by(p -> p.getId() == pid))
                .orElse(personService.all());
    }

    @GetMapping("/byname")
    public List<Person> handle9(@RequestParam("x") String name){
        return personService.all().stream().filter(p -> p.getName().toUpperCase().contains(name.toUpperCase())).collect(Collectors.toList());
    }

}
