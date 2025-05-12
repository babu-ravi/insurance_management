package com.cts.Agent.Management.Module.dto;

import com.cts.Agent.Management.Module.model.Claim;
import lombok.Data;

@Data
public class ClaimStatusUpdateRequest {
    private Claim.ClaimStatus newStatus;

    public Claim.ClaimStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Claim.ClaimStatus newStatus) {
        this.newStatus = newStatus;
    }
}