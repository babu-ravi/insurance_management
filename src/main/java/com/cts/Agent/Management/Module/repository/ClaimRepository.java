package com.cts.Agent.Management.Module.repository;

import com.cts.Agent.Management.Module.model.Claim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    @Query("SELECT c FROM Claim c WHERE " +
            "(:status is null OR c.status = :status) AND " +
            "(:policyId is null OR c.policyId = :policyId) AND " +
            "(:customerId is null OR c.customerId = :customerId)")
    Page<Claim> findClaimsWithFilters(
            @Param("status") Claim.ClaimStatus status,
            @Param("policyId") Long policyId,
            @Param("customerId") Long customerId,
            Pageable pageable
    );
}