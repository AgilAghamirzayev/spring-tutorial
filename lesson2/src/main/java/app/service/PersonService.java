package app.service;

import app.entity.Person;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final List<Person> people = Arrays.asList(
            new Person(1, "Jim"),
            new Person(2, "John"),
            new Person(3, "Jackson")
    );

    public List<Person> all() {
        return people;
    }

    public List<Person> by(Predicate<Person> p) {
        return people.stream().filter(p).collect(Collectors.toList());
    }
}
