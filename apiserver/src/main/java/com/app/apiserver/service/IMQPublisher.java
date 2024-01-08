package com.app.apiserver.service;

public interface IMQPublisher {
    void publish(String queue, String message) throws Exception;
}
