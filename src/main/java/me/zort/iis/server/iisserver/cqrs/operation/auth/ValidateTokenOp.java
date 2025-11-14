package me.zort.iis.server.iisserver.cqrs.operation.auth;

import lombok.Builder;
import lombok.Getter;
import me.zort.iis.server.iisserver.app.auth.AuthFacade;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.user.User;

/**
 * @see AuthFacade#getLoggedInUser(String)
 */
@Getter
@Builder
public class ValidateTokenOp implements Operation<User> {
    private final String token;

}
