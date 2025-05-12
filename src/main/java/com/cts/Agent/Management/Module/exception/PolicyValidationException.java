package com.cts.Agent.Management.Module.exception;

public class PolicyValidationException extends RuntimeException {
    public PolicyValidationException(String message) {
        super(message);
    }
}