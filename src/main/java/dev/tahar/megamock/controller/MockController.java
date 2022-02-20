package dev.tahar.megamock.controller;

import dev.tahar.megamock.model.MockInfo;
import dev.tahar.megamock.model.payload.AddNewMockEndpointForm;
import dev.tahar.megamock.utility.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
public final class MockController {

    private static final String TEMPLATE_MOCKS = "mocks";
    private static final String TEMPLATE_ADD_NEW_MOCK = "mocks-new";
    private static final String TEMPLATE_MOCK_INFO = "mock-info";

    private static final String DEFAULT_CATEGORY_NAME = "uncategorized";

    // Only for debugging purposes
    private final List<MockInfo> inMemoryStorage = new ArrayList<>();

    /**
     * Returns the homepage HTML file called "mocks.html"
     *
     * @return Name of the template to render
     */
    @GetMapping("mocks")
    public String mocks(final Model model) {
        final var mocksGroupedByCategory = new HashMap<String, List<MockInfo>>();

        inMemoryStorage.forEach(mock -> mocksGroupedByCategory
                .computeIfAbsent(mock.getCategory(), s -> new ArrayList<>())
                .add(mock));

        model.addAttribute("data", mocksGroupedByCategory);
        return TEMPLATE_MOCKS;
    }

    /**
     * Returns the page used to create new endpoint mocks
     *
     * @param model Contains all data necessary to render the view
     * @return Name of the template to render
     */
    @GetMapping("mocks/new")
    public String newMock(final Model model) {
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
    public String newMockSubmit(@Valid @ModelAttribute(name = "data") final AddNewMockEndpointForm data, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return TEMPLATE_ADD_NEW_MOCK;
        }

        final var httpMethod = HttpMethod.resolve(data.getMethod().toUpperCase());

        // Store endpoint
        if (httpMethod != null) {
            inMemoryStorage.add(new MockInfo(
                    data.getId(),
                    data.getName(),
                    data.getCategory() == null || data.getCategory().isBlank()
                            ? DEFAULT_CATEGORY_NAME
                            : data.getCategory(),
                    data.getEndpoint(),
                    httpMethod));
        }

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
    public String deleteMockById(@PathVariable UUID id) {
        inMemoryStorage.removeIf(entry -> entry.getId().equals(id));

        // Back to the main mocking overview
        return StringUtils.formatEndpointAsRedirect("/mocks");
    }

    /**
     * Returns a page with more detailed information about a mock
     *
     * @param id Mock ID
     * @return Name of the template to render
     */
    @GetMapping("mocks/{id}")
    public String viewMockInfo(@PathVariable UUID id, final Model model) {
        final var mock = inMemoryStorage
                .stream()
                .filter(mockInfo -> mockInfo.getId().equals(id))
                .findFirst()
                .orElse(null);

        model.addAttribute("data", mock);

        return TEMPLATE_MOCK_INFO;
    }

}
