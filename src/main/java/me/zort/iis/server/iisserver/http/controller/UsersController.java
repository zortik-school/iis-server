package me.zort.iis.server.iisserver.http.controller;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.http.model.user.IdentityResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsersController {

    @GetMapping("/users/me")
    public IdentityResponse getIdentity(@AuthenticationPrincipal User user) {
        return new IdentityResponse(user);
    }
}
