package com.demo.poc.commons.custom.properties.rules;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuleTemplate {

  private String directory;
  private Map<String, String> strategies;
}