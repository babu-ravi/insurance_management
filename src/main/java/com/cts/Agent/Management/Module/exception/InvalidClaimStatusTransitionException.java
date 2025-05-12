package com.cts.Agent.Management.Module.exception;

public class InvalidClaimStatusTransitionException extends RuntimeException {
    private final String currentStatus;
    private final String newStatus;

    public InvalidClaimStatusTransitionException(String currentStatus, String newStatus) {
        super(String.format("Cannot transition claim from %s to %s", currentStatus, newStatus));
        this.currentStatus = currentStatus;
        this.newStatus = newStatus;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getNewStatus() {
        return newStatus;
    }
}