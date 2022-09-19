package com.github.marceloedudev.domain.errors.base;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ErrorMessage {
    private List<String> messages;

    private int code;

    private String status;

    private String timestamp;

    private void Setup(int code) {
        this.code = code;
        this.status = Response.Status.fromStatusCode(code).name();
        LocalDateTime currentTime = LocalDateTime.now();
        this.timestamp = currentTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public ErrorMessage(String message, int code) {
        this.messages = Arrays.asList(message);
        Setup(code);
    }

    public ErrorMessage(List<String> messages, int code) {
        this.messages = messages;
        Setup(code);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
