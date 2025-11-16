package me.zort.iis.server.iisserver.cqrs.operation.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.auth.AuthFacade;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.user.User;

/**
 * @see AuthFacade#getLoggedInUser(String)
 */
@Getter
@RequiredArgsConstructor
public class ValidateTokenOp implements Operation<User> {
    private final String token;

}
