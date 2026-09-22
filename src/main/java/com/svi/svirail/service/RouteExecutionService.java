package com.svi.svirail.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.svi.svirail.config.RouteConfigParser;
import com.svi.svirail.model.ExecutionResult;
import com.svi.svirail.model.RouteDefinition;
import com.svi.svirail.persistence.ExecutionRecord;
import com.svi.svirail.persistence.ExecutionRecordRepository;

@Service
public class RouteExecutionService {
  private final RouteConfigParser parser; private final DesktopAutomationGateway gateway; private final ExecutionRecordRepository records;
  public RouteExecutionService(RouteConfigParser parser, DesktopAutomationGateway gateway, ExecutionRecordRepository records) { this.parser = parser; this.gateway = gateway; this.records = records; }
  public List<RouteDefinition> validate(String config) { return parser.parse(config); }
  public List<ExecutionResult> planOrExecute(String config) {
    return parser.parse(config).stream().map(route -> {
      ExecutionResult result = gateway.execute(route);
      records.save(ExecutionRecord.from(result));
      return result;
    }).toList();
  }
  public List<ExecutionRecord> recentRuns() { return records.findTop100ByOrderByCompletedAtDesc(); }
}
