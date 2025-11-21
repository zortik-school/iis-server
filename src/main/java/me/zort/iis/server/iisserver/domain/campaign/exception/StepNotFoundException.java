package me.zort.iis.server.iisserver.domain.campaign.exception;

public class StepNotFoundException extends RuntimeException {

    public StepNotFoundException(long stepId) {
        super("Step with ID " + stepId + " not found.");
    }
}
