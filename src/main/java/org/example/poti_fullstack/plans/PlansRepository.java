package org.example.poti_fullstack.plans;

import org.example.poti_fullstack.plans.model.Plans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlansRepository extends JpaRepository<Plans, Long> {
}