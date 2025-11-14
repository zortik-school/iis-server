package me.zort.iis.server.iisserver.app.user;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.user.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;

    @Override
    public void deleteUser(long userId) {
        userService.deleteUser(userId);
    }
}
