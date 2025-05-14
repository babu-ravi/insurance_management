package com.cts.Agent.Management.Module.service;
import com.cts.Agent.Management.Module.dto.ClaimStatusUpdateRequest;
import com.cts.Agent.Management.Module.exception.InvalidClaimStatusTransitionException;
import com.cts.Agent.Management.Module.exception.PolicyValidationException;
import com.cts.Agent.Management.Module.model.Policy;
import com.cts.Agent.Management.Module.repository.PolicyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final PolicyRepository policyRepository; // Add this

    public ClaimService(ClaimRepository claimRepository, PolicyRepository policyRepository) {
        this.claimRepository = claimRepository;
        this.policyRepository = policyRepository;
    }
    @Transactional
    public ClaimResponse submitClaim(ClaimCreateRequest request) {
        // Check if policy exists
        Policy policy = (Policy) policyRepository.findByPolicyNumber(request.getPolicyId())
                .orElseThrow(() -> new PolicyValidationException(
                        "Policy not found with policy number: " + request.getPolicyId()));

        // Check if claim amount exceeds coverage amount
        if (request.getAmount().compareTo(policy.getCoverageAmount()) > 0) {
            throw new PolicyValidationException(
                    String.format("Claim amount %s exceeds policy coverage amount %s",
                            request.getAmount(), policy.getCoverageAmount()));
        }


        Claim claim = new Claim();
        claim.setPolicyId(request.getPolicyId());
        claim.setCustomerId(request.getCustomerId());
        claim.setAmount(request.getAmount());
        claim.setDescription(request.getDescription());
        claim.setStatus(Claim.ClaimStatus.FILED);

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

    @Transactional(readOnly = true)
    public Page<ClaimResponse> getClaims(
            Claim.ClaimStatus status,
            Long policyId,
            Long customerId,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Claim> claims = claimRepository.findClaimsWithFilters(status, policyId, customerId, pageable);
        return claims.map(this::convertToResponse);
    }

    @Transactional
    public ClaimResponse updateClaimStatus(Long id, ClaimStatusUpdateRequest request) {
        Claim claim = claimRepository.findById(id)
                .orElseThrow(() -> new ClaimNotFoundException(id));

        validateStatusTransition(claim.getStatus(), request.getNewStatus());

        claim.setStatus(request.getNewStatus());
        claim.setReviewerComments(request.getReviewerComments());
        Claim updatedClaim = claimRepository.save(claim);
        return convertToResponse(updatedClaim);
    }

    private void validateStatusTransition(Claim.ClaimStatus currentStatus, Claim.ClaimStatus newStatus) {
        if (currentStatus == Claim.ClaimStatus.FILED && newStatus == Claim.ClaimStatus.UNDER_REVIEW) {
            return;
        }

        if (currentStatus == Claim.ClaimStatus.UNDER_REVIEW &&
                (newStatus == Claim.ClaimStatus.APPROVED || newStatus == Claim.ClaimStatus.REJECTED)) {
            return;
        }

        throw new InvalidClaimStatusTransitionException(
                currentStatus.toString(),
                newStatus.toString()
        );
    }
}