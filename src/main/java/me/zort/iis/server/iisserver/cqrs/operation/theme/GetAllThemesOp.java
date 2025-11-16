package me.zort.iis.server.iisserver.cqrs.operation.theme;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.campaign.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Getter
@RequiredArgsConstructor
public class GetAllThemesOp implements Operation<Page<Theme>> {
    private final Pageable pageable;

}
