package dev.tahar.megamock.mocking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class MockInfo {

    private UUID id;

    private String name;
    private String category;
    private String endpoint;

    private HttpMethod method;

}
