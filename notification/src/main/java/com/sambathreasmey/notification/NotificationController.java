package com.sambathreasmey.notification;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @PostMapping
    public ResponseEntity<Object> sendNotification(@RequestBody NotificationRequest request) {
        System.out.println(request.chatId() + " : " + request.message());
        return ResponseEntity.ok("Success");
    }
}
