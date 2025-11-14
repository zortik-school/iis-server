package me.zort.iis.server.iisserver.cqrs.operation.auth;

import lombok.Builder;
import lombok.Getter;
import me.zort.iis.server.iisserver.app.auth.JwtPair;
import me.zort.iis.server.iisserver.cqrs.Operation;

@Getter
@Builder
public class RefreshAuthOp implements Operation<JwtPair> {
    private final String refreshToken;

}
