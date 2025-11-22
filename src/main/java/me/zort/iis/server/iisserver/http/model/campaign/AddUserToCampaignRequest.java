package me.zort.iis.server.iisserver.http.model.campaign;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserToCampaignRequest {
    @NotNull
    private long userId;

}
