package app.controller;

import app.service.FormatterLower;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerInject_1 {
    private final FormatterLower fml;
    public ControllerInject_1(FormatterLower fml) {
        this.fml = fml;
    }
}
