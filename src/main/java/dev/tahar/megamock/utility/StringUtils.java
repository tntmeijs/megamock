package dev.tahar.megamock.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

    /**
     * Utility method to make it easier to redirect to a different endpoint
     *
     * @param endpoint Endpoint to redirect to
     * @return Properly formatted redirect string
     */
    public static String formatEndpointAsRedirect(final @NonNull String endpoint) {
        return String.format("redirect:%s", endpoint);
    }

}
