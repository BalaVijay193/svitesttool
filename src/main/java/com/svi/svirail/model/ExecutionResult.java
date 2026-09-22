package com.svi.svirail.model;

import java.time.Instant;

public record ExecutionResult(String routeName, RouteOperation operation, Status status, String detail, long durationMillis, Instant completedAt) {
  public enum Status { SUCCESS, FAILURE, SIMULATED }
}
