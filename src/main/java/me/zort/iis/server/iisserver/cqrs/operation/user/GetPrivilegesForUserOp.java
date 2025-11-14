package me.zort.iis.server.iisserver.cqrs.operation.user;

import lombok.Builder;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import me.zort.iis.server.iisserver.domain.user.User;

import java.util.List;

@Getter
@Builder
public class GetPrivilegesForUserOp implements Operation<List<Privilege>> {
    private final User user;

}
