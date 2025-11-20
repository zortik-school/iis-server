package me.zort.iis.server.iisserver.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserQueryService {

    /**
     * Queries users by given query string.
     *
     * @param query the query string to search users
     * @param pageable pagination information
     * @return a page of users matching the query
     */
    Page<User> query(String query, Pageable pageable);
}
