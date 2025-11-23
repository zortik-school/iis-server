package me.zort.iis.server.iisserver.domain.access.exception;

public class UserNotInCampaignZone extends RuntimeException {

    public UserNotInCampaignZone() {
        super("User is not in the campaign zone.");
    }
}
