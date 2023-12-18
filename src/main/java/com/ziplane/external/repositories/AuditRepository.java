package com.ziplane.external.repositories;

import com.ziplane.external.entities.AuditDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuditRepository extends JpaRepository<AuditDetails, Integer> {
    Optional<List<AuditDetails>> findAllByCaseNumber(String caseNumber);
}
