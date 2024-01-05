package com.app.apiserver.service;

import com.app.apiserver.model.basic.MQPublish;

public interface IMQPublisherService {
    void publish(MQPublish payload) throws Exception;
}
