package app.service;

import org.springframework.stereotype.Service;

@Service
public class FormatterLower implements Formatter{

    @Override
    public String format(String s) {
        return s.toLowerCase();
    }
}
