package com.jsun.site;

import java.util.Collection;

import org.springframework.scheduling.annotation.Async;

public interface NotificationService {
  @Async
  void sendNotification(String subject, String message, Collection<String> recipients);
}
