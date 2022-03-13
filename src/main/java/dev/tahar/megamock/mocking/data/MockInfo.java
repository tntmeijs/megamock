package dev.tahar.megamock.mocking.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class MockInfo {

    /**
     * Unique {@link UUID} that identifies this mock
     */
    private UUID id;

    /**
     * Name of this mock
     */
    private String title;

    /**
     * Category this mock belongs to
     */
    private String category;

    /**
     * Endpoint (pattern) to mock
     */
    private String endpoint;

    /**
     * Supported HTTP method
     */
    private HttpMethod httpMethod;

}
