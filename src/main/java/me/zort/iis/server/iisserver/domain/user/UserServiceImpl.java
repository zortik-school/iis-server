package me.zort.iis.server.iisserver.domain.user;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.user.event.UserCreatedEvent;
import me.zort.iis.server.iisserver.domain.user.event.UserDeletedEvent;
import me.zort.iis.server.iisserver.domain.user.event.UserRoleChangedEvent;
import me.zort.iis.server.iisserver.domain.user.exception.UserConflictException;
import me.zort.iis.server.iisserver.domain.user.exception.UserNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public User createUser(CreateUserArgs args) {
        if (getUserByUsername(args.getUsername()).isPresent()) {
            throw new UserConflictException(UserConflictException.ConflictType.USERNAME_TAKEN);
        }

        User user = new UserImpl(-1, args.getUsername(), args.getPasswordHash(), args.getName(), args.getRole());

        user = userRepository.save(user);

        eventPublisher.publishEvent(new UserCreatedEvent(user));

        return user;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);

        eventPublisher.publishEvent(new UserDeletedEvent(id));
    }

    @Override
    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void setUserRole(long id, Role role) {
        Objects.requireNonNull(role, "Role cannot be null");

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        Role oldRole = user.getRole();
        if (oldRole != role) {
            user.setRole(role);

            userRepository.save(user);

            eventPublisher.publishEvent(new UserRoleChangedEvent(user, oldRole, role));
        }
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
