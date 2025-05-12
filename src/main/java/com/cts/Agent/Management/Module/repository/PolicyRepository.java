package com.cts.Agent.Management.Module.repository;

import com.cts.Agent.Management.Module.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    Optional<Policy> findByPolicyNumber(Long policyNumber);
}