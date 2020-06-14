package app.controller;

import app.service.Formatter;
import org.springframework.stereotype.Controller;


@Controller
public class ControllerInject_2 {
    private final Formatter fml;

    public ControllerInject_2(Formatter fml) {
        this.fml = fml;
    }
}
