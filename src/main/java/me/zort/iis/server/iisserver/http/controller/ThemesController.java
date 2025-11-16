package me.zort.iis.server.iisserver.http.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.cqrs.OperationExecutor;
import me.zort.iis.server.iisserver.cqrs.operation.theme.*;
import me.zort.iis.server.iisserver.domain.campaign.Theme;
import me.zort.iis.server.iisserver.http.model.BlankResponse;
import me.zort.iis.server.iisserver.http.model.PageResponse;
import me.zort.iis.server.iisserver.http.model.theme.CreateThemeRequest;
import me.zort.iis.server.iisserver.http.model.theme.InspectThemeResponse;
import me.zort.iis.server.iisserver.http.model.theme.ThemeModel;
import me.zort.iis.server.iisserver.http.model.theme.UpdateThemeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ThemesController {
    private final OperationExecutor operationExecutor;

    @PostMapping("/themes")
    public BlankResponse createTheme(@Valid @RequestBody CreateThemeRequest body) {
        operationExecutor.dispatch(new CreateThemeOp(body.getName(), body.getDescription()));

        return BlankResponse.getInstance();
    }

    @DeleteMapping("/themes/{id}")
    public BlankResponse deleteTheme(@PathVariable long id) {
        operationExecutor.dispatch(new DeleteThemeOp(id));

        return BlankResponse.getInstance();
    }

    @PutMapping("/themes/{id}")
    public BlankResponse updateTheme(@PathVariable long id, @Valid @RequestBody UpdateThemeRequest body) {
        operationExecutor.dispatch(new UpdateThemeOp(id, body.getName(), body.getDescription()));

        return BlankResponse.getInstance();
    }

    @GetMapping("/themes/{id}")
    public ThemeModel getTheme(@PathVariable long id) {
        Theme theme = operationExecutor.dispatch(new GetThemeOp(id));

        return new ThemeModel(theme);
    }

    @GetMapping("/themes/{id}/inspect")
    public InspectThemeResponse inspectTheme(@PathVariable long id) {
        InspectThemeOp.Result result = operationExecutor.dispatch(new InspectThemeOp(id));

        return new InspectThemeResponse(result.getTheme());
    }

    @GetMapping("/themes")
    public PageResponse<ThemeModel> getThemes(Pageable pageable) {
        Page<Theme> themes = operationExecutor.dispatch(new GetAllThemesOp(pageable));

        return PageResponse.fromPage(themes, ThemeModel::new);
    }
}
