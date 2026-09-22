package com.svi.svirail.web;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.svi.svirail.model.ExecutionResult;
import com.svi.svirail.model.RouteDefinition;
import com.svi.svirail.service.RouteExecutionService;
import com.svi.svirail.persistence.ExecutionRecord;

@RestController
@RequestMapping("/api/routes")
public class RouteController {
  private final RouteExecutionService service;
  public RouteController(RouteExecutionService service) { this.service = service; }
  @PostMapping("/validate") public List<RouteDefinition> validate(@RequestBody @NotBlank String config) { return service.validate(config); }
  @PostMapping("/runs") @ResponseStatus(HttpStatus.ACCEPTED)
  public List<ExecutionResult> run(@RequestBody @NotBlank String config) { return service.planOrExecute(config); }
  @GetMapping("/runs") public List<ExecutionRecord> recentRuns() { return service.recentRuns(); }
}
