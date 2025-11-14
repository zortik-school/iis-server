package me.zort.iis.server.iisserver.mapper.jpa;

import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.domain.user.Role;
import org.springframework.stereotype.Component;

@Component
public class JpaRoleMapper implements JpaMapper<Role, me.zort.iis.server.iisserver.data.jpa.entity.Role> {

    @Override
    public Role toDomain(me.zort.iis.server.iisserver.data.jpa.entity.Role entity) {
        return switch (entity) {
            case ADMIN -> Role.ADMIN;
            case USER -> Role.USER;
            default -> throw new IllegalArgumentException("Unknown role entity: " + entity);
        };
    }

    @Override
    public me.zort.iis.server.iisserver.data.jpa.entity.Role toEntity(Role domain) {
        return switch (domain) {
            case ADMIN -> me.zort.iis.server.iisserver.data.jpa.entity.Role.ADMIN;
            case USER -> me.zort.iis.server.iisserver.data.jpa.entity.Role.USER;
        };
    }
}
