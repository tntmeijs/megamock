package dev.tahar.megamock.controller;

import dev.tahar.megamock.model.payload.NewMockData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public final class MockController {

    // Only for debugging purposes
    private final List<NewMockData> inMemoryStorage = new ArrayList<>();

    /**
     * Returns the homepage HTML file called "mocks.html"
     *
     * @return Name of the mocks' HTML file
     */
    @GetMapping("mocks")
    private String mocks(final Model model) {
        model.addAttribute("mocks", inMemoryStorage);
        return "mocks";
    }

    @GetMapping("mocks/new")
    private String newMock(final Model model) {
        model.addAttribute("data", new NewMockData());
        return "mocks-new";
    }

    @PostMapping("mocks/new")
    private String newMockSubmit(@ModelAttribute(name = "data") final NewMockData data, final Model model) {
        inMemoryStorage.add(data);
        return "redirect:/mocks";
    }

}
