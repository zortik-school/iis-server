package me.zort.iis.server.iisserver.domain.campaign;

public interface CreateActivityArgs {

    String getName();

    String getDescription();

    long getStepId();

    long getStartDate();

    long getEndDate();
}
