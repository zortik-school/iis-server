package me.zort.iis.server.iisserver.domain.user.impl;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.domain.user.UserQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository repository;

    @Override
    public Page<User> query(String query, Pageable pageable) {
        return repository.findAllByNameContaining(query, pageable);
    }
}
