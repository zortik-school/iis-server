package me.zort.iis.server.iisserver.cqrs.operation.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.user.User;

@Getter
@AllArgsConstructor
public class GetUserOp implements Operation<User> {
    private final long userId;

}
