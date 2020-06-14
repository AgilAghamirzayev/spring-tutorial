package com.iba.lessons.lesson1.controller;

import com.iba.lessons.lesson1.entity.Person;
import com.iba.lessons.lesson1.service.CalcService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class PersonController {

    private final CalcService calcService;

    public PersonController(CalcService calcService) {
        this.calcService = calcService;
    }


    @RequestMapping({"/","/hello"})
    public String hello(@RequestParam(value = "name", defaultValue = "World", required = true) String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }


    @RequestMapping("persons")
    public List<Person> handle(){
        return Arrays.asList(
                new Person("Ali"),
                new Person("Asif")
                );
    }

    @RequestMapping("calc")
    public String sum(){
        int c = calcService.add(1,2);
        return Integer.toString(c);
    }

    @RequestMapping("calc/mul")
    public String mul(){
        int m = calcService.mul(9,9);
        return Integer.toString(m);
    }

}
