package me.zort.iis.server.iisserver.http.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {
    private final T data;

}
