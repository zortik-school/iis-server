package me.zort.iis.server.iisserver.app.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtPair {
    private final String accessToken;
    private final String refreshToken;

}
