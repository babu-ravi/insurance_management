package com.cts.Agent.Management.Module.dto;

import com.cts.Agent.Management.Module.model.Claim;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ClaimSearchCriteria {
    private Claim.ClaimStatus status;
    private Long policyId;
    private Long customerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}