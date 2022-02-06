package dev.tahar.megamock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class HomeController {

    /**
     * Returns the homepage HTML file called "index.html"
     *
     * @return Name of the homepage's HTML file
     */
    @GetMapping("/")
    private String index() {
        return "index";
    }

}
