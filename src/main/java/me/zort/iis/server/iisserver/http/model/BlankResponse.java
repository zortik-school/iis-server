package me.zort.iis.server.iisserver.http.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.jetbrains.annotations.NotNull;

@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BlankResponse {
    private static BlankResponse INSTANCE = null;

    public BlankResponse() {
    }

    /**
     * Returns a singleton instance of BlankResponse.
     *
     * @return the singleton instance
     */
    public static @NotNull BlankResponse getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BlankResponse();
        }

        return INSTANCE;
    }
}
