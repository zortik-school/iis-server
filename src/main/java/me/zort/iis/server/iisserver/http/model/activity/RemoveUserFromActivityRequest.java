package me.zort.iis.server.iisserver.http.model.activity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveUserFromActivityRequest {
    @NotNull
    private long userId;

}
