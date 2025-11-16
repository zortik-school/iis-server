package me.zort.iis.server.iisserver.cqrs.operation.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.auth.JwtPair;
import me.zort.iis.server.iisserver.cqrs.Operation;

@Getter
@RequiredArgsConstructor
public class RegisterOp implements Operation<JwtPair> {
    private final String username;
    private final String name;
    private final String password;

}
