package me.zort.iis.server.iisserver.domain.auth.impl;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.auth.BasicCredentialsService;
import me.zort.iis.server.iisserver.domain.auth.exception.InvalidCredentialsException;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicCredentialsServiceImpl implements BasicCredentialsService {
    private final PasswordEncoder passwordEncoder;

    @Override
    public void validatePassword(String password, @Nullable String passwordHash) {
        if (passwordHash == null || !passwordEncoder.matches(password, passwordHash)) {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
