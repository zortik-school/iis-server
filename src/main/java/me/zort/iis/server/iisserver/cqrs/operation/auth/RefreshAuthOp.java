package me.zort.iis.server.iisserver.cqrs.operation.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.auth.JwtPair;
import me.zort.iis.server.iisserver.cqrs.Operation;

@Getter
@RequiredArgsConstructor
public class RefreshAuthOp implements Operation<JwtPair> {
    private final String refreshToken;

}
