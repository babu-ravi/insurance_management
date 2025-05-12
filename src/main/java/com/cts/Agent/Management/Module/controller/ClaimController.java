package com.cts.Agent.Management.Module.controller;

import com.cts.Agent.Management.Module.dto.ClaimCreateRequest;
import com.cts.Agent.Management.Module.dto.ClaimResponse;
import com.cts.Agent.Management.Module.dto.ClaimStatusUpdateRequest;
import com.cts.Agent.Management.Module.model.Claim;
import com.cts.Agent.Management.Module.service.ClaimService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @PostMapping
    public ResponseEntity<ClaimResponse> submitClaim(@RequestBody ClaimCreateRequest request) {
        ClaimResponse response = claimService.submitClaim(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaimResponse> getClaimById(@PathVariable Long id) {
        ClaimResponse claim = claimService.getClaimById(id);
        return ResponseEntity.ok(claim);
    }
    @GetMapping
    public ResponseEntity<Page<ClaimResponse>> getClaims(
            @RequestParam(required = false) Claim.ClaimStatus status,
            @RequestParam(required = false) Long policyId,
            @RequestParam(required = false) Long customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ClaimResponse> claims = claimService.getClaims(status, policyId, customerId, page, size);
        return ResponseEntity.ok(claims);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ClaimResponse> updateClaimStatus(
            @PathVariable Long id,
            @RequestBody ClaimStatusUpdateRequest request) {
        ClaimResponse response = claimService.updateClaimStatus(id, request);
        return ResponseEntity.ok(response);
    }
}