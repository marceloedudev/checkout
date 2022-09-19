package com.github.marceloedudev.infra.dto;

public class PlaceOrderResponse {
    private Boolean success;
    private String message;

    public PlaceOrderResponse() {
    }

    public PlaceOrderResponse(String message) {
        this.success = true;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
