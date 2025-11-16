package me.zort.iis.server.iisserver.domain.campaign;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CampaignImpl implements Campaign {
    private final long id;
    private final String name;
    private final Long assignedUserId;
    private final long themeId;

}
