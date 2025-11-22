package me.zort.iis.server.iisserver.domain.campaign.impl;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.campaign.*;
import me.zort.iis.server.iisserver.domain.campaign.event.ThemeCreatedEvent;
import me.zort.iis.server.iisserver.domain.campaign.event.ThemeUpdatedEvent;
import me.zort.iis.server.iisserver.domain.campaign.exception.ThemeNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {
    private final ThemeRepository themeRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Theme createTheme(CreateThemeArgs args) {
        Theme theme = new ThemeImpl(-1, args.getName(), args.getDescription());
        theme = themeRepository.save(theme);

        eventPublisher.publishEvent(new ThemeCreatedEvent(theme.getId()));

        return theme;
    }

    @Override
    public Theme updateTheme(UpdateThemeArgs args) {
        long themeId = args.getThemeId();

        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new ThemeNotFoundException(themeId));
        theme.setName(args.getName());
        theme.setDescription(args.getDescription());

        eventPublisher.publishEvent(new ThemeUpdatedEvent(themeId));

        return themeRepository.save(theme);
    }

    @Override
    public void deleteTheme(long themeId) {
        themeRepository.deleteById(themeId);
    }

    @Override
    public Optional<Theme> getTheme(long themeId) {
        return themeRepository.findById(themeId);
    }

    @Override
    public Page<Theme> getAllThemes(Pageable pageable) {
        return themeRepository.findAll(pageable);
    }
}
