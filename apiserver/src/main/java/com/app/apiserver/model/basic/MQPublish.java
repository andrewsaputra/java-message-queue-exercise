package com.app.apiserver.model.basic;

public record MQPublish(
        String queue,
        String message
) {
}
