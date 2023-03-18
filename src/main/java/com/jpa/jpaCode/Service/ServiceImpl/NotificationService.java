package com.jpa.jpaCode.Service.ServiceImpl;

import com.jpa.jpaCode.model.GeneralApiResponse;
import com.jpa.jpaCode.model.NotificationRequestDto;
import com.jpa.jpaCode.model.SubscriptionRequestDto;

import java.util.List;

public interface NotificationService {

    void pushNotificationOnDevice(String clientId, String title, String body);

    GeneralApiResponse<Object> subscribeTopic(SubscriptionRequestDto subscriptionRequestDto);

    GeneralApiResponse<Object> unSubscribeTopic(SubscriptionRequestDto subscriptionRequestDto);

    GeneralApiResponse<Object> pushNotificationByTopic(NotificationRequestDto notificationRequestDto);

    GeneralApiResponse<Object> createGroupTokens(String notificationKeyName, List<String> tokens);

    GeneralApiResponse<Object> addDeviceByNotificationKey(String notificationKey, String notificationKeyName, List<String> tokens);

}
