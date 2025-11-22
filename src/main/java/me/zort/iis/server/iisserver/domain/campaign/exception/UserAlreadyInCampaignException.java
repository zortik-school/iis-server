package me.zort.iis.server.iisserver.domain.campaign.exception;

public class UserAlreadyInCampaignException extends RuntimeException {

    public UserAlreadyInCampaignException() {
        super("User is already assigned to the campaign.");
    }
}
