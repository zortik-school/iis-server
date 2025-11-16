package me.zort.iis.server.iisserver.cqrs.operation.user;

import lombok.Builder;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Getter
@Builder
public class GetUsersOp implements Operation<Page<User>> {
    private final Pageable pageable;

}
