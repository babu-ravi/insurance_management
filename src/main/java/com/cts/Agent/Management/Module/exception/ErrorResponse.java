package com.cts.Agent.Management.Module.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(int value, String message) {
    }
}