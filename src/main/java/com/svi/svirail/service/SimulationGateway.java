package com.svi.svirail.service;

import java.time.Instant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.svi.svirail.model.ExecutionResult;
import com.svi.svirail.model.RouteDefinition;

/** Safe default: validates planned work but never controls WESTCAD or GSIM. */
@Component
@Profile("!desktop-runner")
public class SimulationGateway implements DesktopAutomationGateway {
  @Override public ExecutionResult execute(RouteDefinition route) {
    long elapsed = Math.max(1, route.steps().size());
    return new ExecutionResult(route.routeName(), route.operation(), ExecutionResult.Status.SIMULATED,
        "No desktop control performed; configure an approved desktop runner.", elapsed, Instant.now());
  }
}
