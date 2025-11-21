package me.zort.iis.server.iisserver.domain.campaign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class StepImpl implements Step {
    private final long id;
    private final String name;
    private final long campaignId;
    @Setter
    private boolean active;

    private Long assignedUserId;

}
