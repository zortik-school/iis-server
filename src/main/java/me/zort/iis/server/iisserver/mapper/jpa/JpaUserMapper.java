package me.zort.iis.server.iisserver.mapper.jpa;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.UserEntity;
import me.zort.iis.server.iisserver.domain.user.Role;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.domain.user.impl.UserImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaUserMapper implements JpaMapper<User, UserEntity> {
    private final JpaMapper<Role, me.zort.iis.server.iisserver.data.jpa.entity.Role> roleMapper;

    @Override
    public User toDomain(UserEntity entity) {
        return new UserImpl(
                entity.getId(),
                entity.getUsername(),
                entity.getPasswordHash(), entity.getName(), roleMapper.toDomain(entity.getRole()));
    }

    @Override
    public UserEntity toEntity(User domain) {
        return new UserEntity(
                domain.getId(),
                domain.getUsername(),
                domain.getPasswordHash(),
                domain.getName(), roleMapper.toEntity(domain.getRole()), List.of(), List.of(), List.of());
    }
}
