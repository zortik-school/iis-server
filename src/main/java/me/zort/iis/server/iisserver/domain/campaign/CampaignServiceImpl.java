package me.zort.iis.server.iisserver.domain.campaign;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {
    private final CampaignRepository repository;

    @Override
    public Page<Campaign> getAssignedCampaigns(long userId, Pageable pageable) {
        return repository.findAllByAssignedUserId(userId, pageable);
    }

    @Override
    public Page<Campaign> getAllCampaigns(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Campaign> getAllCampaignsForTheme(long themeId, Pageable pageable) {
        return repository.findAllByThemeId(themeId, pageable);
    }
}
