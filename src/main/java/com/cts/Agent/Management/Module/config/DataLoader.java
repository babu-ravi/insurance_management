package com.cts.Agent.Management.Module.config;

import com.cts.Agent.Management.Module.model.Policy;
import com.cts.Agent.Management.Module.repository.PolicyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(PolicyRepository repository) {
        return args -> {
            // Check if data already exists
            if (repository.count() > 0) {
                return; // Skip initialization if data exists
  }
            List<Policy> policies = new ArrayList<>();

            policies.add(new Policy(null, 111L, new BigDecimal("100000.00"), new BigDecimal("1000.00")));
            policies.add(new Policy(null, 112L, new BigDecimal("150000.00"), new BigDecimal("1500.00")));
            policies.add(new Policy(null, 113L, new BigDecimal("200000.00"), new BigDecimal("2000.00")));
            policies.add(new Policy(null, 114L, new BigDecimal("75000.00"), new BigDecimal("750.00")));
            policies.add(new Policy(null, 115L, new BigDecimal("300000.00"), new BigDecimal("3000.00")));
            policies.add(new Policy(null, 116L, new BigDecimal("250000.00"), new BigDecimal("2500.00")));
            policies.add(new Policy(null, 117L, new BigDecimal("180000.00"), new BigDecimal("1800.00")));
            policies.add(new Policy(null, 118L, new BigDecimal("120000.00"), new BigDecimal("1200.00")));
            policies.add(new Policy(null, 119L, new BigDecimal("90000.00"), new BigDecimal("900.00")));
            policies.add(new Policy(null, 120L, new BigDecimal("175000.00"), new BigDecimal("1750.00")));
            policies.add(new Policy(null, 121L, new BigDecimal("225000.00"), new BigDecimal("2250.00")));
            policies.add(new Policy(null, 122L, new BigDecimal("135000.00"), new BigDecimal("1350.00")));
            policies.add(new Policy(null, 123L, new BigDecimal("280000.00"), new BigDecimal("2800.00")));
            policies.add(new Policy(null, 124L, new BigDecimal("165000.00"), new BigDecimal("1650.00")));
            policies.add(new Policy(null, 125L, new BigDecimal("195000.00"), new BigDecimal("1950.00")));
            policies.add(new Policy(null, 126L, new BigDecimal("145000.00"), new BigDecimal("1450.00")));
            policies.add(new Policy(null, 127L, new BigDecimal("210000.00"), new BigDecimal("2100.00")));
            policies.add(new Policy(null, 128L, new BigDecimal("155000.00"), new BigDecimal("1550.00")));
            policies.add(new Policy(null, 129L, new BigDecimal("270000.00"), new BigDecimal("2700.00")));
            policies.add(new Policy(null, 130L, new BigDecimal("185000.00"), new BigDecimal("1850.00")));

            repository.saveAll(policies);
        };
    }
}