package com.cts.Agent.Management.Module.exception;

public class ClaimNotFoundException extends RuntimeException {
    public ClaimNotFoundException(Long id) {
        super("Claim not found with id: " + id);
    }
}