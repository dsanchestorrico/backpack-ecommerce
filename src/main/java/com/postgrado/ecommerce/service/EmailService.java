package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.EmailNotification;

public interface EmailService {
  String send(EmailNotification email);
}
