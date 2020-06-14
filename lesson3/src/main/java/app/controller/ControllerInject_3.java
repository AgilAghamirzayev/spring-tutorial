package app.controller;

import app.service.Formatter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;


@Controller
public class ControllerInject_3 {

    private final Formatter fml;

    public ControllerInject_3(@Qualifier("formatterLower") Formatter fml) {
        this.fml = fml;
    }
}
