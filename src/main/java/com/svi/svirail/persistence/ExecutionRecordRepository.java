package com.svi.svirail.persistence;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionRecordRepository extends JpaRepository<ExecutionRecord, UUID> {
  List<ExecutionRecord> findTop100ByOrderByCompletedAtDesc();
}
