package me.zort.iis.server.iisserver.app.query;

import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueryFacade {

    /**
     * Queries users based on the provided query string and pagination information.
     *
     * @param query the search query string
     * @param pageable pagination information
     * @return a page of users matching the query
     */
    Page<User> queryUsers(String query, Pageable pageable);
}
