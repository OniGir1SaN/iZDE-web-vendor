package com.demoqa.enums.iZDEvendor;

import lombok.Getter;

public enum EndpointsV {

    SIGNUP("sign-up"),
    SIGNIN("sign-in"),
    CLIENTS("clients"),
    OBJECTS("objects"),
    SUPPORT("support"),
    PROFILE("profile/personal-data");

    @Getter
    private final String endpoint;

    EndpointsV(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return this.endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
