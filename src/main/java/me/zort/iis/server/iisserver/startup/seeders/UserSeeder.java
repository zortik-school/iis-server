package me.zort.iis.server.iisserver.startup.seeders;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.auth.AuthFacade;
import me.zort.iis.server.iisserver.app.auth.JwtPair;
import me.zort.iis.server.iisserver.app.auth.RegisterArgs;
import me.zort.iis.server.iisserver.app.user.UserFacade;
import me.zort.iis.server.iisserver.domain.user.Role;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSeeder implements Seeder {
    private final UserFacade userFacade;
    private final AuthFacade authFacade;

    @Override
    public void seed() {
        if (!userFacade.getUsers(PageRequest.of(0, 1)).isEmpty()) {
            return;
        }

        // Create admin user
        User admin = registerUser(RegisterArgs.builder()
                .name("Dummy Admin")
                .username("admin")
                .password("admin")
                .build());
        userFacade.setUserRole(admin.getId(), Role.ADMIN);

        User campaignManager = registerUser(RegisterArgs.builder()
                .name("Campaign Manager")
                .username("campaignmanager")
                .password("campaignmanager")
                .build());
        userFacade.setUserRole(campaignManager.getId(), Role.CAMPAIGN_MANAGER);

        User stepManager = registerUser(RegisterArgs.builder()
                .name("Step Manager")
                .username("stepmanager")
                .password("stepmanager")
                .build());
        userFacade.setUserRole(stepManager.getId(), Role.STEP_MANAGER);

        User activityExecutor = registerUser(RegisterArgs.builder()
                .name("Activity Executor")
                .username("activityexecutor")
                .password("activityexecutor")
                .build());
        userFacade.setUserRole(activityExecutor.getId(), Role.ACTIVITY_EXECUTOR);

        // Create normal user
        registerUser(RegisterArgs.builder()
                .name("Dummy User")
                .username("user")
                .password("user")
                .build());
    }

    private User registerUser(RegisterArgs args) {
        JwtPair jwtPair = authFacade.register(args);

        return authFacade.getLoggedInUser(jwtPair.getAccessToken());
    }
}
