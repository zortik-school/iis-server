package me.zort.iis.server.iisserver.http.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.auth.JwtPair;
import me.zort.iis.server.iisserver.cqrs.OperationExecutor;
import me.zort.iis.server.iisserver.cqrs.operation.auth.LoginOp;
import me.zort.iis.server.iisserver.cqrs.operation.auth.RefreshAuthOp;
import me.zort.iis.server.iisserver.cqrs.operation.auth.RegisterOp;
import me.zort.iis.server.iisserver.http.model.auth.JwtResponse;
import me.zort.iis.server.iisserver.http.model.auth.LoginRequest;
import me.zort.iis.server.iisserver.http.model.auth.RegisterRequest;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final OperationExecutor operationExecutor;

    @PostMapping("/auth/login")
    public JwtResponse login(@RequestBody LoginRequest body, HttpServletResponse response) {
        LoginOp op = LoginOp.builder()
                .username(body.getUsername())
                .password(body.getPassword())
                .build();

        JwtPair jwt = operationExecutor.dispatch(op);
        writeRefreshTokenCookie(response, jwt);

        return new JwtResponse(jwt.getAccessToken());
    }

    @PostMapping("/auth/register")
    public JwtResponse register(@RequestBody RegisterRequest body, HttpServletResponse response) {
        RegisterOp op = RegisterOp.builder()
                .username(body.getUsername())
                .name(body.getName())
                .password(body.getPassword())
                .build();

        JwtPair jwt = operationExecutor.dispatch(op);
        writeRefreshTokenCookie(response, jwt);

        return new JwtResponse(jwt.getAccessToken());
    }

    @GetMapping("/auth/refresh")
    public JwtResponse refresh(@CookieValue("refresh-token") String refreshToken, HttpServletResponse response) {
        RefreshAuthOp op = RefreshAuthOp.builder()
                .refreshToken(refreshToken)
                .build();

        JwtPair newPair = operationExecutor.dispatch(op);
        writeRefreshTokenCookie(response, newPair);

        return new JwtResponse(newPair.getAccessToken());
    }

    /**
     * Writes the refresh token as an HTTP-only cookie in the response.
     *
     * @param response the HTTP response
     * @param newPair the new JWT pair containing the refresh token
     */
    private static void writeRefreshTokenCookie(HttpServletResponse response, JwtPair newPair) {
        ResponseCookie cookie = ResponseCookie.from("refresh-token", newPair.getRefreshToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("None")
                .maxAge(60L * 60L * 24L * 30L) // 30 days
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }
}
