package dev.tahar.megamock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class MockController {

    /**
     * Returns the homepage HTML file called "mocks.html"
     *
     * @return Name of the mocks' HTML file
     */
    @GetMapping("mocks")
    private String mocks() {
        return "mocks";
    }

}
