package me.zort.iis.server.iisserver.cqrs.handler.auth;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.auth.AuthFacade;
import me.zort.iis.server.iisserver.app.auth.JwtPair;
import me.zort.iis.server.iisserver.app.auth.RegisterArgs;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.auth.RegisterOp;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterHandler implements OperationHandler<RegisterOp, JwtPair> {
    private final AuthFacade authFacade;

    @Override
    public JwtPair handle(RegisterOp operation) {
        RegisterArgs args = RegisterArgs.builder()
                .username(operation.getUsername())
                .name(operation.getName())
                .password(operation.getPassword())
                .build();

        return authFacade.register(args);
    }

    @Override
    public Class<RegisterOp> getOperationType() {
        return RegisterOp.class;
    }
}
