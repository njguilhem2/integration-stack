package com.bff.integration.model;

public class KeyResponse {
    private String id;

    public KeyResponse(){}
    public KeyResponse(String id) {
        this.id = id;
    }

    public String getArn() {
        return id;
    }

    public void setArn(String id) {
        this.id = id;
    }
}
