package me.zort.iis.server.iisserver.app.auth;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.auth.BasicCredentialsService;
import me.zort.iis.server.iisserver.domain.auth.JwtService;
import me.zort.iis.server.iisserver.domain.auth.exception.InvalidCredentialsException;
import me.zort.iis.server.iisserver.domain.auth.exception.TokenInvalidException;
import me.zort.iis.server.iisserver.domain.user.CreateUserArgs;
import me.zort.iis.server.iisserver.domain.user.Role;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.domain.user.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade {
    private final UserService userService;
    private final JwtService jwtService;
    private final BasicCredentialsService credentialsService;

    @Override
    public JwtPair login(LoginArgs args) {
        User user = userService.getUserByUsername(args.getUsername())
                .orElseThrow(InvalidCredentialsException::new);
        credentialsService.validatePassword(args.getPassword(), user.getPasswordHash());

        return generateJwtPairForUser(user.getId());
    }

    @Override
    public JwtPair register(RegisterArgs args) {
        CreateUserArgs createUserArgs = CreateUserArgs.builder()
                .username(args.getUsername())
                .passwordHash(credentialsService.hashPassword(args.getPassword()))
                .name(args.getName())
                .role(Role.USER)
                .build();
        User user = userService.createUser(createUserArgs);

        return generateJwtPairForUser(user.getId());
    }

    @Override
    public JwtPair refreshSession(String refreshToken) {
        long userId = jwtService.validateToken(refreshToken);

        return generateJwtPairForUser(userId);
    }

    @Override
    public User getLoggedInUser(String token) {
        long userId = jwtService.validateToken(token);

        return userService.getUser(userId).orElseThrow(TokenInvalidException::new);
    }

    /**
     * Generates JWT access and refresh tokens for the given user.
     *
     * @param userId The ID of the user for whom to generate the tokens.
     * @return A JwtPair containing the generated access and refresh tokens.
     */
    private JwtPair generateJwtPairForUser(long userId) {
        String accessToken = jwtService.generateAccessToken(
                userId);
        String refreshToken = jwtService.generateRefreshToken(
                userId);

        return JwtPair.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
