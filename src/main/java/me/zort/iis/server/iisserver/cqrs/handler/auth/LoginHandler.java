package me.zort.iis.server.iisserver.cqrs.handler.auth;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.auth.AuthFacade;
import me.zort.iis.server.iisserver.app.auth.JwtPair;
import me.zort.iis.server.iisserver.app.auth.LoginArgs;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.auth.LoginOp;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginHandler implements OperationHandler<LoginOp, JwtPair> {
    private final AuthFacade authFacade;

    @Override
    public JwtPair handle(LoginOp operation) {
        LoginArgs args = LoginArgs.builder()
                .username(operation.getUsername())
                .password(operation.getPassword())
                .build();

        return authFacade.login(args);
    }

    @Override
    public Class<LoginOp> getOperationType() {
        return LoginOp.class;
    }
}
