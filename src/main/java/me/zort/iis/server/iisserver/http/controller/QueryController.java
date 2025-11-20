package me.zort.iis.server.iisserver.http.controller;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.cqrs.OperationExecutor;
import me.zort.iis.server.iisserver.cqrs.operation.query.QueryUsersOp;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.http.model.PageResponse;
import me.zort.iis.server.iisserver.http.model.user.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query")
@RequiredArgsConstructor
public class QueryController {
    private final OperationExecutor operationExecutor;

    @GetMapping("/users")
    public PageResponse<UserModel> queryUsers(@RequestParam("query") String query, Pageable pageable) {
        Page<User> page = operationExecutor.dispatch(new QueryUsersOp(query, pageable));

        return PageResponse.fromPage(page, UserModel::new);
    }
}
