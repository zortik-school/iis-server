package me.zort.iis.server.iisserver.app.query;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.domain.user.UserQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryFacadeImpl implements QueryFacade {
    private final UserQueryService userQueryService;

    @Override
    public Page<User> queryUsers(String query, Pageable pageable) {
        return userQueryService.query(query, pageable);
    }
}
