package me.zort.iis.server.iisserver.domain.campaign.exception;

public class UserNotInCampaignException extends RuntimeException {

    public UserNotInCampaignException() {
        super("User is not part of the specified campaign.");
    }
}
