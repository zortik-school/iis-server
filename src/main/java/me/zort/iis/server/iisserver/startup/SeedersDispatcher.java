package me.zort.iis.server.iisserver.startup;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.startup.seeders.Seeder;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeedersDispatcher implements ApplicationListener<ApplicationReadyEvent> {
    private final List<Seeder> seeders;
    private final AppStateService stateService;

    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        if (stateService.getActiveProfile() != AppProfile.DEV) {
            // Seeders are only run in DEV profile
            return;
        }

        for (Seeder seeder : seeders) {
            seeder.seed();
        }
    }
}
