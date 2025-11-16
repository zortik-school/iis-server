package me.zort.iis.server.iisserver.cqrs.operation.campaigns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class GetThemeCampaignsOp implements Operation<Page<Campaign>> {
    private final long themeId;
    private final Pageable pageable;

}
