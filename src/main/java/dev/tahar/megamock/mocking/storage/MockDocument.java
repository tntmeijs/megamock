package dev.tahar.megamock.mocking.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpMethod;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "mocks")
public final class MockDocument {

    /**
     * Unique {@link UUID} that identifies this mock
     */
    @Id
    @Indexed
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
