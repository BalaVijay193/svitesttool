package com.svi.svirail.model;

import java.util.List;

public record RouteDefinition(String routeName, RouteOperation operation, List<String> steps, String sourceLine) { }
