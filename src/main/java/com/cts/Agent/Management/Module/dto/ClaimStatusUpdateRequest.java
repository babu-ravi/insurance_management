package com.cts.Agent.Management.Module.dto;

import com.cts.Agent.Management.Module.model.Claim;
import lombok.Data;

@Data
public class ClaimStatusUpdateRequest {
    public Claim.ClaimStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Claim.ClaimStatus newStatus) {
        this.newStatus = newStatus;
    }

    public String getReviewerComments() {
        return reviewerComments;
    }

    public void setReviewerComments(String reviewerComments) {
        this.reviewerComments = reviewerComments;
    }

    private Claim.ClaimStatus newStatus;
    private String reviewerComments;
}