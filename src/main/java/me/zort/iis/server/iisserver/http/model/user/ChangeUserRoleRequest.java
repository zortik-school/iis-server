package me.zort.iis.server.iisserver.http.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.zort.iis.server.iisserver.domain.user.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeUserRoleRequest {
    private Role role;

}
