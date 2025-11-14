package me.zort.iis.server.iisserver.http.model;

import org.jetbrains.annotations.NotNull;

public final class BlankResponse {
    private static BlankResponse INSTANCE = null;

    private BlankResponse() {
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
