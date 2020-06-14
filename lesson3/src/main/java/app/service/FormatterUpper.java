package app.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class FormatterUpper implements Formatter{
    @Override
    public String format(String s) {
        return s.toUpperCase();
    }
}
