package com.andresFVA.turnosOdont.ControllersMVC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class MvcLoginController {
    @GetMapping
    public String login() {
        return "login";
    }
}
