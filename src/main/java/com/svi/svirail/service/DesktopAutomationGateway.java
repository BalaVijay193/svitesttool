package com.svi.svirail.service;

import com.svi.svirail.model.ExecutionResult;
import com.svi.svirail.model.RouteDefinition;

/** Boundary for a separately controlled Windows/SikuliX test runner. */
public interface DesktopAutomationGateway {
  ExecutionResult execute(RouteDefinition route);
}
