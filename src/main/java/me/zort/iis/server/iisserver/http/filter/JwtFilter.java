package me.zort.iis.server.iisserver.http.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.cqrs.OperationExecutor;
import me.zort.iis.server.iisserver.cqrs.operation.auth.ValidateTokenOp;
import me.zort.iis.server.iisserver.domain.user.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final OperationExecutor operationExecutor;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {
        String token = extractTokenFromRequest(request);

        if (token != null) {
            ValidateTokenOp op = ValidateTokenOp.builder()
                    .token(token)
                    .build();
            User user = operationExecutor.dispatch(op);
            SecurityContextHolder.getContext().setAuthentication(
                    UsernamePasswordAuthenticationToken.authenticated(user, token, List.of(user.getRole())));
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Extracts the JWT token from the HTTP request.
     *
     * @param request the HTTP request
     * @return the extracted JWT token
     */
    private static @Nullable String extractTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return null;
    }
}
