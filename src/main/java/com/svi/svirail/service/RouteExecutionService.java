package com.svi.svirail.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.svi.svirail.config.RouteConfigParser;
import com.svi.svirail.model.ExecutionResult;
import com.svi.svirail.model.RouteDefinition;

@Service
public class RouteExecutionService {
  private final RouteConfigParser parser; private final DesktopAutomationGateway gateway;
  public RouteExecutionService(RouteConfigParser parser, DesktopAutomationGateway gateway) { this.parser = parser; this.gateway = gateway; }
  public List<RouteDefinition> validate(String config) { return parser.parse(config); }
  public List<ExecutionResult> planOrExecute(String config) { return parser.parse(config).stream().map(gateway::execute).toList(); }
}
