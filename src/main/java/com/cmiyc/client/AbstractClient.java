package com.cmiyc.client;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PROTECTED)
public abstract class AbstractClient {

    private final String baseUrl;
    private final String authorization;

    public AbstractClient(String baseUrl, String authorization) {
        this.baseUrl = baseUrl;
        this.authorization = authorization;
    }
}
