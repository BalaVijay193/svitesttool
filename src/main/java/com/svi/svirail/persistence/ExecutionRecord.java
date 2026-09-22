package com.svi.svirail.persistence;

import com.svi.svirail.model.ExecutionResult;
import com.svi.svirail.model.RouteOperation;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "execution_record")
public class ExecutionRecord {
  @Id private UUID id;
  private String routeName;
  @Enumerated(EnumType.STRING) private RouteOperation operation;
  @Enumerated(EnumType.STRING) private ExecutionResult.Status status;
  @Column(length = 2000) private String detail;
  private long durationMillis;
  private Instant completedAt;
  protected ExecutionRecord() { }
  public static ExecutionRecord from(ExecutionResult result) {
    ExecutionRecord record = new ExecutionRecord(); record.id = UUID.randomUUID(); record.routeName = result.routeName();
    record.operation = result.operation(); record.status = result.status(); record.detail = result.detail();
    record.durationMillis = result.durationMillis(); record.completedAt = result.completedAt(); return record;
  }
  public UUID getId() { return id; } public String getRouteName() { return routeName; } public RouteOperation getOperation() { return operation; }
  public ExecutionResult.Status getStatus() { return status; } public String getDetail() { return detail; } public long getDurationMillis() { return durationMillis; } public Instant getCompletedAt() { return completedAt; }
}
