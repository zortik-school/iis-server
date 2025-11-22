package me.zort.iis.server.iisserver.http.model.activity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateActivityRequest {
    @NotBlank
    private String name;

    @NotNull
    private String description;

    @NotNull
    private long stepId;

    @NotNull
    @Positive
    private long startDate;

    @NotNull
    @Positive
    private long endDate;

}
