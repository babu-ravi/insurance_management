package com.cts.Agent.Management.Module.controller;

import com.cts.Agent.Management.Module.dto.ClaimCreateRequest;
import com.cts.Agent.Management.Module.dto.ClaimResponse;
import com.cts.Agent.Management.Module.service.ClaimService;
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
}