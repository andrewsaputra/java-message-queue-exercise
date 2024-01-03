package com.app.apiserver.model.basic;

public record ApiResponse(
        String message,
        Object data
) {
}
