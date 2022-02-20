package dev.tahar.megamock.model.payload;

import lombok.Data;

@Data
public final class NewMockData {

    private String name;
    private String category;
    private String method;
    private String endpoint;

}
