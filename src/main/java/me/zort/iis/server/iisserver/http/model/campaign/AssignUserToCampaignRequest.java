package me.zort.iis.server.iisserver.http.model.campaign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignUserToCampaignRequest {
    private Long userId;

}
