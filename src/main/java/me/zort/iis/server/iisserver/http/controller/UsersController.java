package me.zort.iis.server.iisserver.http.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.cqrs.OperationExecutor;
import me.zort.iis.server.iisserver.cqrs.operation.user.*;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.http.model.BlankResponse;
import me.zort.iis.server.iisserver.http.model.PageResponse;
import me.zort.iis.server.iisserver.http.model.user.ChangePasswordRequest;
import me.zort.iis.server.iisserver.http.model.user.ChangeUserRoleRequest;
import me.zort.iis.server.iisserver.http.model.user.IdentityResponse;
import me.zort.iis.server.iisserver.http.model.user.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final OperationExecutor operationExecutor;

    @GetMapping("/users")
    public PageResponse<UserModel> getUsers(
            @RequestParam(value = "activityId", required = false) Long activityId,
            Pageable pageable) {
        Page<User> page;
        if (activityId != null) {
            page = operationExecutor.dispatch(new GetUsersForActivityOp(activityId, pageable));
        } else {
            page = operationExecutor.dispatch(new GetUsersOp(pageable));
        }

        return PageResponse.fromPage(page, UserModel::new);
    }

    @GetMapping("/users/me")
    public IdentityResponse getIdentity(@AuthenticationPrincipal User user) {
        List<Privilege> privileges = operationExecutor.dispatch(new GetPrivilegesForUserOp(user));

        return new IdentityResponse(user, privileges);
    }

    @PutMapping("/users/me/password")
    public BlankResponse changePassword(@Valid @RequestBody ChangePasswordRequest body) {
        operationExecutor.dispatch(new ChangePasswordOp(body.getOldPassword(), body.getNewPassword()));

        return BlankResponse.getInstance();
    }

    @GetMapping("/users/{id}")
    public UserModel getUserById(@PathVariable long id) {
        User user = operationExecutor.dispatch(new GetUserOp(id));

        return new UserModel(user);
    }

    @DeleteMapping("/users/{id}")
    public BlankResponse deleteUser(@PathVariable long id) {
        operationExecutor.dispatch(new DeleteUserOp(id));

        return BlankResponse.getInstance();
    }

    @PutMapping("/users/{id}/role")
    public BlankResponse changeUserRole(@PathVariable long id, @Valid @RequestBody ChangeUserRoleRequest body) {
        operationExecutor.dispatch(new ChangeUserRoleOp(id, body.getRole()));

        return BlankResponse.getInstance();
    }
}
