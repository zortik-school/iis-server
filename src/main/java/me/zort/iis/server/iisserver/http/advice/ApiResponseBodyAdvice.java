package me.zort.iis.server.iisserver.http.advice;

import me.zort.iis.server.iisserver.http.model.ApiResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NotNull MethodParameter returnType, @NotNull Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            @NotNull MethodParameter returnType,
            @NotNull MediaType selectedContentType,
            @NotNull Class selectedConverterType,
            @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {
        if (body instanceof ApiResponse<?>) {
            return body;
        } else {
            return new ApiResponse<>(body);
        }
    }
}
