package com.cts.Agent.Management.Module.exception;

public class ClaimNotFoundException extends RuntimeException {
    public ClaimNotFoundException(Long id) {
        super(String.format("Claim not found with ID: %d", id));
    }
}