package com.jpa.jpaCode.Controller;


import com.jpa.jpaCode.Service.ServiceImpl.NotificationService;
import com.jpa.jpaCode.model.GeneralApiResponse;
import com.jpa.jpaCode.model.NotificationRequestDto;
import com.jpa.jpaCode.model.SubscriptionRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/notification")
@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/{clientId}")
    public void pushNotification(@PathVariable String clientId, @RequestParam String title, @RequestParam String body) {
        notificationService.pushNotificationOnDevice(clientId, title, body);
    }

    @PostMapping("/topic/subscribe")
    public GeneralApiResponse subscribeTopic(@RequestBody SubscriptionRequestDto subscriptionRequest) {
        return notificationService.subscribeTopic(subscriptionRequest);
    }

    @PostMapping("/topic/unsubscribe")
    public GeneralApiResponse unSubscribeTopic(@RequestBody SubscriptionRequestDto subscriptionRequest) {
        return notificationService.unSubscribeTopic(subscriptionRequest);
    }

    @PostMapping("/topic/send")
    public GeneralApiResponse pushNotification(@RequestBody NotificationRequestDto notificationRequest) {
        return notificationService.pushNotificationByTopic(notificationRequest);
    }

}
