package me.zort.iis.server.iisserver.cqrs.operation.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class GetUsersForActivityOp implements Operation<Page<User>> {
    private final long activityId;
    private final Pageable pageable;

}
