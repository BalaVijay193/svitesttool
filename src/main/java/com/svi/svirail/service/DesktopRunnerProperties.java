package com.svi.svirail.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "runner")
public class DesktopRunnerProperties {
  private boolean enabled; private String command = ""; private long timeoutSeconds = 600;
  public boolean isEnabled() { return enabled; } public void setEnabled(boolean enabled) { this.enabled = enabled; }
  public String getCommand() { return command; } public void setCommand(String command) { this.command = command; }
  public long getTimeoutSeconds() { return timeoutSeconds; } public void setTimeoutSeconds(long timeoutSeconds) { this.timeoutSeconds = timeoutSeconds; }
}
