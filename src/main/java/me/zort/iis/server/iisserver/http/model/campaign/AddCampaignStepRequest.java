package me.zort.iis.server.iisserver.http.model.campaign;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCampaignStepRequest {
    @NotBlank
    private String name;
    @NotNull
    private long campaignId;

}
