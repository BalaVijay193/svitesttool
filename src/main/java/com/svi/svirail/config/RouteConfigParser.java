package com.svi.svirail.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.svi.svirail.model.RouteDefinition;
import com.svi.svirail.model.RouteOperation;

/** Parses legacy NAME-OPERATION=step^step records without interpreting GUI commands. */
@Component
public class RouteConfigParser {
  public List<RouteDefinition> parse(String text) {
    List<RouteDefinition> routes = new ArrayList<>();
    for (String raw : text.split("\\R")) {
      String line = raw.trim();
      if (line.isBlank() || line.startsWith("#")) continue;
      int equals = line.indexOf('=');
      int dash = equals < 0 ? -1 : line.lastIndexOf('-', equals);
      if (equals < 1 || dash < 1) throw new IllegalArgumentException("Invalid route configuration: " + raw);
      String route = line.substring(0, dash).trim();
      RouteOperation operation = RouteOperation.valueOf(line.substring(dash + 1, equals).trim());
      List<String> steps = List.of(line.substring(equals + 1).split("\\^"));
      routes.add(new RouteDefinition(route, operation, steps, raw));
    }
    return routes;
  }
}
