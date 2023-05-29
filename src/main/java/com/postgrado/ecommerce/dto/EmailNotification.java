package com.postgrado.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Getter
@Setter
public class EmailNotification {
  private String to;
  private String subject;
  private String body;
  private boolean hasTemplate;
}
