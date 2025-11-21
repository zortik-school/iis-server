package me.zort.iis.server.iisserver.http.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    @NotBlank
    private String oldPassword;
    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String newPassword;

}
