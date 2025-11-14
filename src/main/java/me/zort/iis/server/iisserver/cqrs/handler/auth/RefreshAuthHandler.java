package me.zort.iis.server.iisserver.cqrs.handler.auth;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.auth.AuthFacade;
import me.zort.iis.server.iisserver.app.auth.JwtPair;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.auth.RefreshAuthOp;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshAuthHandler implements OperationHandler<RefreshAuthOp, JwtPair> {
    private final AuthFacade authFacade;

    @Override
    public JwtPair handle(RefreshAuthOp operation) {
        return authFacade.refreshSession(operation.getRefreshToken());
    }

    @Override
    public Class<RefreshAuthOp> getOperationType() {
        return RefreshAuthOp.class;
    }
}
