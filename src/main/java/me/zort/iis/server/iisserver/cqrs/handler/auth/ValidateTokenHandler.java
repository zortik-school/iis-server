package me.zort.iis.server.iisserver.cqrs.handler.auth;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.auth.AuthFacade;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.auth.ValidateTokenOp;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ValidateTokenHandler implements OperationHandler<ValidateTokenOp, User> {
    private final AuthFacade authFacade;

    @Override
    public User handle(ValidateTokenOp operation) {
        String token = Objects.requireNonNull(operation.getToken());

        return authFacade.getLoggedInUser(token);
    }

    @Override
    public Class<ValidateTokenOp> getOperationType() {
        return ValidateTokenOp.class;
    }
}
