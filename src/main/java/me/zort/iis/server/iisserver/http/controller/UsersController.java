package me.zort.iis.server.iisserver.http.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.cqrs.OperationExecutor;
import me.zort.iis.server.iisserver.cqrs.operation.user.ChangeUserRoleOp;
import me.zort.iis.server.iisserver.cqrs.operation.user.DeleteUserOp;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.http.model.BlankResponse;
import me.zort.iis.server.iisserver.http.model.user.ChangeUserRoleRequest;
import me.zort.iis.server.iisserver.http.model.user.IdentityResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final OperationExecutor operationExecutor;

    @GetMapping("/users/me")
    public IdentityResponse getIdentity(@AuthenticationPrincipal User user) {
        return new IdentityResponse(user);
    }

    @DeleteMapping("/users/{id}")
    public BlankResponse deleteUser(@PathVariable long id) {
        DeleteUserOp op = DeleteUserOp.builder()
                .userId(id)
                .build();
        operationExecutor.dispatch(op);

        return BlankResponse.getInstance();
    }

    @PutMapping("/users/{id}/role")
    public BlankResponse changeUserRole(@PathVariable long id, @Valid ChangeUserRoleRequest body) {
        ChangeUserRoleOp op = ChangeUserRoleOp.builder()
                .userId(id)
                .role(body.getRole())
                .build();
        operationExecutor.dispatch(op);

        return BlankResponse.getInstance();
    }
}
