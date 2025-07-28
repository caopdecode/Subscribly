package com.caopdecode.subscribly.notification;

import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService{
    @Override
    public void sendNotification(String to, String message){
        System.out.println("[Email sended to: " + to + "]");
        System.out.println("Email content: " + message);
    }
}
