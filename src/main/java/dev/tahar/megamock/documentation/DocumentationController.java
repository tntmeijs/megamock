package dev.tahar.megamock.documentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class DocumentationController {

    /**
     * Returns the documentation HTML file called "documentation.html"
     *
     * @return Name of the documentation's HTML file
     */
    @GetMapping("/documentation")
    private String documentation() {
        return "documentation";
    }

}
