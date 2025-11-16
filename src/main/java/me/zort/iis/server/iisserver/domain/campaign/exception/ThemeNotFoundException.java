package me.zort.iis.server.iisserver.domain.campaign.exception;

import lombok.Getter;

@Getter
public class ThemeNotFoundException extends RuntimeException {
    private final long themeId;

    public ThemeNotFoundException(long themeId) {
        super("Theme with ID " + themeId + " not found.");
        this.themeId = themeId;
    }
}
