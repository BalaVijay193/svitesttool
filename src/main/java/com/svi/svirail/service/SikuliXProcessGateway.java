package com.svi.svirail.service;

import com.svi.svirail.model.ExecutionResult;
import com.svi.svirail.model.RouteDefinition;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/** Runs only an explicitly configured local Windows runner command. */
@Component
@Profile("desktop-runner")
@EnableConfigurationProperties(DesktopRunnerProperties.class)
public class SikuliXProcessGateway implements DesktopAutomationGateway {
  private final DesktopRunnerProperties settings;
  public SikuliXProcessGateway(DesktopRunnerProperties settings) { this.settings = settings; }
  @Override public ExecutionResult execute(RouteDefinition route) {
    Instant started = Instant.now();
    if (!settings.isEnabled() || settings.getCommand().isBlank()) return result(route, ExecutionResult.Status.FAILURE, "Desktop runner is disabled or runner.command is not configured.", started);
    try {
      ProcessBuilder process = new ProcessBuilder(List.of("cmd", "/c", settings.getCommand()));
      process.environment().put("SVI_ROUTE_NAME", route.routeName());
      process.environment().put("SVI_OPERATION", route.operation().name());
      process.environment().put("SVI_LEGACY_CONFIG_LINE", route.sourceLine());
      process.redirectErrorStream(true);
      Process child = process.start();
      boolean completed = child.waitFor(settings.getTimeoutSeconds(), TimeUnit.SECONDS);
      if (!completed) { child.destroyForcibly(); return result(route, ExecutionResult.Status.FAILURE, "Desktop runner timed out.", started); }
      String output = new String(child.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
      return result(route, child.exitValue() == 0 ? ExecutionResult.Status.SUCCESS : ExecutionResult.Status.FAILURE, output, started);
    } catch (IOException exception) { return result(route, ExecutionResult.Status.FAILURE, exception.getMessage(), started); }
      catch (InterruptedException exception) { Thread.currentThread().interrupt(); return result(route, ExecutionResult.Status.FAILURE, "Desktop runner interrupted.", started); }
  }
  private ExecutionResult result(RouteDefinition route, ExecutionResult.Status status, String detail, Instant started) {
    return new ExecutionResult(route.routeName(), route.operation(), status, detail, java.time.Duration.between(started, Instant.now()).toMillis(), Instant.now());
  }
}
