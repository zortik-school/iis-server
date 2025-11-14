package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
