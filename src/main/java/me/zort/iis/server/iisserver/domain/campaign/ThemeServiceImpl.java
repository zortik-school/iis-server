package me.zort.iis.server.iisserver.domain.campaign;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {
    private final ThemeRepository themeRepository;

    @Override
    public Page<Theme> getAllThemes(Pageable pageable) {
        return themeRepository.findAll(pageable);
    }
}
