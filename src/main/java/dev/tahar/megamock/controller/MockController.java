package dev.tahar.megamock.controller;

import dev.tahar.megamock.model.MockInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller()
public final class MockController {

    /**
     * Returns the homepage HTML file called "mocks.html"
     *
     * @return Name of the mocks' HTML file
     */
    @GetMapping("mocks")
    private String mocks(final Model model) {
        model.addAttribute("mocks", List.of(
                new MockInfo("/v1/hello/world"),
                new MockInfo("/v2/hello/world"),
                new MockInfo("/v3/hello/world")));

        return "mocks";
    }

}
