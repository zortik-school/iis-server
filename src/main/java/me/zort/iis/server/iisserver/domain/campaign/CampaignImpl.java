package me.zort.iis.server.iisserver.domain.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CampaignImpl implements Campaign {
    private final long id;
    private final String name;
    @Setter
    private Long assignedUserId;
    private final long themeId;

}
