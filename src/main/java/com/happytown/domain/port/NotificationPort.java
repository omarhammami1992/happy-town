package com.happytown.domain.port;

import com.happytown.domain.entity.Notification;

public interface NotificationPort {
   void notify(Notification notification);
}
