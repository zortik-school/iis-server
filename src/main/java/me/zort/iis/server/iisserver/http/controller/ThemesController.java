package me.zort.iis.server.iisserver.http.controller;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.cqrs.OperationExecutor;
import me.zort.iis.server.iisserver.cqrs.operation.theme.GetAllThemesOp;
import me.zort.iis.server.iisserver.domain.campaign.Theme;
import me.zort.iis.server.iisserver.http.model.PageResponse;
import me.zort.iis.server.iisserver.http.model.theme.ThemeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ThemesController {
    private final OperationExecutor operationExecutor;

    @GetMapping("/themes")
    public PageResponse<ThemeModel> getThemes(Pageable pageable) {
        Page<Theme> themes = operationExecutor.dispatch(new GetAllThemesOp(pageable));

        return PageResponse.fromPage(themes, ThemeModel::new);
    }
}
