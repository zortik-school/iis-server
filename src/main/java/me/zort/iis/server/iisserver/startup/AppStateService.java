package me.zort.iis.server.iisserver.startup;

public interface AppStateService {

    /**
     * Returns the currently active application profile.
     *
     * @return the active AppProfile
     * @throws IllegalStateException if any issue occurs
     */
    AppProfile getActiveProfile();
}
