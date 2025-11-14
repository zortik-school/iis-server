package me.zort.iis.server.iisserver.domain.user;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.user.event.UserCreatedEvent;
import me.zort.iis.server.iisserver.domain.user.event.UserDeletedEvent;
import me.zort.iis.server.iisserver.domain.user.exception.UserConflictException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

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
}
