package me.zort.iis.server.iisserver.app.user;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.user.Role;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.domain.user.UserService;
import me.zort.iis.server.iisserver.domain.user.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;

    @Override
    public void setUserRole(long userId, Role role) {
        userService.setUserRole(userId, role);
    }

    @Override
    public void deleteUser(long userId) {
        userService.getUser(userId).orElseThrow(() -> new UserNotFoundException(userId));

        userService.deleteUser(userId);
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }
}
