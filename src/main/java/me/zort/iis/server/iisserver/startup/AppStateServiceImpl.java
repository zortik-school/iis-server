package me.zort.iis.server.iisserver.startup;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppStateServiceImpl implements AppStateService {
    private final Environment environment;

    @Override
    public AppProfile getActiveProfile() {
        List<AppProfile> appProfiles = matchProfiles(environment.getActiveProfiles());

        if (appProfiles.isEmpty()) {
            throw new IllegalStateException("App has no active profile!");
        } else if (appProfiles.size() > 1) {
            throw new IllegalStateException("App has multiple active profiles: " + appProfiles);
        } else {
            return appProfiles.get(0);
        }
    }

    /**
     * Matches the given profiles to the defined AppProfile enum values.
     *
     * @param profiles the array of profile names to match
     * @return a list of matched AppProfile enum values
     */
    private static List<AppProfile> matchProfiles(String[] profiles) {
        List<AppProfile> appProfiles = new ArrayList<>();
        for (String profile : profiles) {
            try {
                AppProfile appProfile = AppProfile.valueOf(profile.toUpperCase());

                appProfiles.add(appProfile);
            } catch (IllegalArgumentException ignored) {
                // Ignore unknown profiles
            }
        }

        return appProfiles;
    }
}
