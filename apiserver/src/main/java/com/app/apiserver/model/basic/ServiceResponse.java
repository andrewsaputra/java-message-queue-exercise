package com.app.apiserver.model.basic;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class ServiceResponse {
    private int code;
    private Body body;
    private Exception exc;


    @Getter
    @Builder
    public static class Body {
        private Object data;
        private String message;
    }
}
