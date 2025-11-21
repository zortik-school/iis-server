package me.zort.iis.server.iisserver.cqrs.handler.user;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.auth.AuthFacade;
import me.zort.iis.server.iisserver.app.auth.LoginArgs;
import me.zort.iis.server.iisserver.app.user.UserFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.user.ChangePasswordOp;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangePasswordHandler extends CommandHandler<ChangePasswordOp> {
    private final AuthFacade authFacade;
    private final UserFacade userFacade;
    private final UserProvider userProvider;

    @Override
    public void execute(ChangePasswordOp operation) {
        User user = userProvider.getCurrentUser();

        // Verify old password
        LoginArgs loginArgs = LoginArgs.builder()
                .username(user.getUsername())
                .password(operation.getOldPassword())
                .build();
        authFacade.login(loginArgs);

        // Change to new password
        userFacade.changePassword(user.getId(), operation.getNewPassword());
    }

    @Override
    public Class<ChangePasswordOp> getOperationType() {
        return ChangePasswordOp.class;
    }
}
