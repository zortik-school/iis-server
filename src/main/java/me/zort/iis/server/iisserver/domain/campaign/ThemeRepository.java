package me.zort.iis.server.iisserver.domain.campaign;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ThemeRepository {

    /**
     * Finds all themes with pagination.
     *
     * @param pageable pagination information
     * @return a page of themes
     */
    Page<Theme> findAll(Pageable pageable);
}
