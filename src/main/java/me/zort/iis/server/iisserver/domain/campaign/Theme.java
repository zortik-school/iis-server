package me.zort.iis.server.iisserver.domain.campaign;

public interface Theme {

    void setName(String name);

    void setDescription(String description);

    long getId();

    String getName();

    String getDescription();
}
