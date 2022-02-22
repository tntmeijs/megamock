package dev.tahar.megamock.mocking;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public final class AddNewMockEndpointForm {

    private final UUID id = UUID.randomUUID();

    @NotBlank(message = "")
    @Size(min = 1, message = "Endpoint name cannot be blank and its name should have a length of at least 1 character")
    private String name;

    private String category;

    @NotBlank(message = "Invalid HTTP method")
    private String method;

    @NotBlank(message = "")
    @Size(min = 1, message = "Endpoint cannot be blank and its name should have a length of at least 1 character")
    private String endpoint;

}
