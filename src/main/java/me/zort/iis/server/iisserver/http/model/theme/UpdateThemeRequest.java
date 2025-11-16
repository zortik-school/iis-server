package me.zort.iis.server.iisserver.http.model.theme;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateThemeRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;

}
