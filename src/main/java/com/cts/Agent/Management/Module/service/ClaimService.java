package com.cts.Agent.Management.Module.service;

import com.cts.Agent.Management.Module.dto.ClaimCreateRequest;
import com.cts.Agent.Management.Module.dto.ClaimResponse;
import com.cts.Agent.Management.Module.exception.ClaimNotFoundException;
import com.cts.Agent.Management.Module.model.Claim;
import com.cts.Agent.Management.Module.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @Transactional
    public ClaimResponse submitClaim(ClaimCreateRequest request) {
        Claim claim = new Claim();
        claim.setPolicyId(request.getPolicyId());
        claim.setCustomerId(request.getCustomerId());
        claim.setAmount(request.getAmount());
        claim.setDescription(request.getDescription());
        claim.setStatus(Claim.ClaimStatus.FILED);

        // Save to database
        Claim savedClaim = claimRepository.save(claim);
        return convertToResponse(savedClaim);
    }

    private ClaimResponse convertToResponse(Claim claim) {
        ClaimResponse response = new ClaimResponse();
        response.setId(claim.getId());
        response.setPolicyId(claim.getPolicyId());
        response.setCustomerId(claim.getCustomerId());
        response.setAmount(claim.getAmount());
        response.setDescription(claim.getDescription());
        response.setStatus(claim.getStatus());
        response.setReviewerComments(claim.getReviewerComments());
        response.setCreatedAt(claim.getCreatedAt());
        response.setUpdatedAt(claim.getUpdatedAt());
        return response;
    }

    @Transactional(readOnly = true)
    public ClaimResponse getClaimById(Long id) {
        Claim claim = claimRepository.findById(id)
                .orElseThrow(() -> new ClaimNotFoundException(id));
        return convertToResponse(claim);
    }
}