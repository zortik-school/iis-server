package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.jpa.entity.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaThemeRepository extends JpaRepository<ThemeEntity, Long> {
}
