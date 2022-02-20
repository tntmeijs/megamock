package dev.tahar.megamock.controller;

import dev.tahar.megamock.model.payload.AddNewMockEndpointForm;
import dev.tahar.megamock.utility.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public final class MockController {

    private final static String TEMPLATE_MOCKS = "mocks";
    private final static String TEMPLATE_ADD_NEW_MOCK = "mocks-new";

    // Only for debugging purposes
    private final List<AddNewMockEndpointForm> inMemoryStorage = new ArrayList<>();

    /**
     * Returns the homepage HTML file called "mocks.html"
     *
     * @return Name of the template to render
     */
    @GetMapping("mocks")
    private String mocks(final Model model) {
        model.addAttribute("mocks", inMemoryStorage);
        return TEMPLATE_MOCKS;
    }

    /**
     * Returns the page used to create new endpoint mocks
     *
     * @param model Contains all data necessary to render the view
     * @return Name of the template to render
     */
    @GetMapping("mocks/new")
    private String newMock(final Model model) {
        model.addAttribute("data", new AddNewMockEndpointForm());
        return TEMPLATE_ADD_NEW_MOCK;
    }

    /**
     * Endpoint that receives data from the new mock form
     *
     * @param data          Form data
     * @param bindingResult Validation errors
     * @return Name of the template to render
     */
    @PostMapping("mocks/new")
    private String newMockSubmit(@Valid @ModelAttribute(name = "data") final AddNewMockEndpointForm data, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return TEMPLATE_ADD_NEW_MOCK;
        }

        // Store endpoint
        inMemoryStorage.add(data);

        // Back to the main mocking overview
        return StringUtils.formatEndpointAsRedirect("/mocks");
    }

    /**
     * Endpoint to remove existing mocks
     *
     * @param id Unique ID of the mock that should be removed
     * @return Name of the template to render
     */
    @GetMapping("mocks/{id}/delete")
    private String deleteMockById(@PathVariable UUID id) {
        inMemoryStorage.removeIf(entry -> entry.getId().equals(id));

        // Back to the main mocking overview
        return StringUtils.formatEndpointAsRedirect("/mocks");
    }

}
