package me.zort.iis.server.iisserver.app.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterArgs {
    private final String username;
    private final String name;

    /**
     * Optional. (Note: currently required until new options appear here)
     * If not provided, we suppose that the user will is authenticated otherwise (e.g., OAuth).
     */
    @Builder.Default
    private final String password = null;

}
