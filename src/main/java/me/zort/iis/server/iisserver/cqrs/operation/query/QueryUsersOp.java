package me.zort.iis.server.iisserver.cqrs.operation.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class QueryUsersOp implements Operation<Page<User>> {
    private final String query;
    private final Pageable pageable;

}
