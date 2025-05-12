package com.cts.Agent.Management.Module.repository;

import com.cts.Agent.Management.Module.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository <Claim, Long> {

}
