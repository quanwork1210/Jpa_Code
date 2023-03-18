package com.jpa.jpaCode.Service.ServiceImpl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import com.jpa.jpaCode.Constants.Constants;
import com.jpa.jpaCode.Enum.StatusEnum;
import com.jpa.jpaCode.model.GeneralApiResponse;
import com.jpa.jpaCode.model.NotificationRequestDto;
import com.jpa.jpaCode.model.SubscriptionRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Value("${app.firebase-config}")
    private String firebaseConfig;

    private FirebaseApp firebaseApp;

    @PostConstruct
    private void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfig).getInputStream())).build();

            if (FirebaseApp.getApps().isEmpty()) {
                this.firebaseApp = FirebaseApp.initializeApp(options);

            } else {
                this.firebaseApp = FirebaseApp.getInstance();
            }
        } catch (IOException e) {
            log.error("Create FirebaseApp Error", e);
        }
    }


    public GeneralApiResponse subscribeToTopic(SubscriptionRequestDto subscriptionRequestDto) {
        try {
            FirebaseMessaging.getInstance(firebaseApp).subscribeToTopic(subscriptionRequestDto.getTokens(),
                    subscriptionRequestDto.getTopicName());

            return new GeneralApiResponse<>(StatusEnum.SUCCESS.getValue(), Constants.SUCCESS);
        } catch (FirebaseMessagingException e) {
            log.error("Firebase subscribe to topic fail", e);
            return new GeneralApiResponse<>(StatusEnum.FAILED.getValue(), Constants.FAILED);
        }
    }

    public GeneralApiResponse unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto) {
        try {
            FirebaseMessaging.getInstance(firebaseApp).unsubscribeFromTopic(subscriptionRequestDto.getTokens(),
                    subscriptionRequestDto.getTopicName());
            return new GeneralApiResponse<>(StatusEnum.SUCCESS.getValue(), Constants.SUCCESS);
        } catch (FirebaseMessagingException e) {
            log.error("Firebase unsubscribe from topic fail", e);
            return new GeneralApiResponse<>(StatusEnum.FAILED.getValue(), Constants.FAILED);
        }
    }

    public String sendPnsToDevice(NotificationRequestDto notificationRequestDto) {
        Message message = Message.builder()
                .setToken(notificationRequestDto.getTarget())
                .setNotification(Notification.builder()
                        .setTitle(notificationRequestDto.getTitle())
                        .setBody(notificationRequestDto.getBody())
                        .setImage("")
                        .build())
                .putData("content", notificationRequestDto.getTitle())
                .putData("body", notificationRequestDto.getBody())
                .build();

        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            log.error("Fail to send firebase notification", e);
        }

        return response;
    }

    public GeneralApiResponse sendPnsToTopic(NotificationRequestDto notificationRequestDto) {
        Message message = Message.builder()
                .setTopic(notificationRequestDto.getTopic())
                .setNotification(Notification.builder()
                        .setTitle(notificationRequestDto.getTitle())
                        .setBody(notificationRequestDto.getBody())
                        .setImage("")
                        .build())
                .putData("content", notificationRequestDto.getTitle())
                .putData("body", notificationRequestDto.getBody())
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
            return new GeneralApiResponse<>(StatusEnum.SUCCESS.getValue(), Constants.SUCCESS);
        } catch (FirebaseMessagingException e) {
            log.error("Fail to send firebase notification", e);
            return new GeneralApiResponse<>(StatusEnum.FAILED.getValue(), Constants.FAILED);
        }
    }


    @Override
    public void pushNotificationOnDevice(String clientId, String title, String body) {
        sendPnsToDevice(NotificationRequestDto.builder().target(clientId).title(title).body(body).build());
    }

    @Override
    public GeneralApiResponse<Object> subscribeTopic(SubscriptionRequestDto subscriptionRequestDto) {
       return this.subscribeToTopic(subscriptionRequestDto);
    }

    @Override
    public GeneralApiResponse<Object> unSubscribeTopic(SubscriptionRequestDto subscriptionRequestDto) {
        return this.unsubscribeFromTopic(subscriptionRequestDto);
    }

    @Override
    public GeneralApiResponse<Object> pushNotificationByTopic(NotificationRequestDto notificationRequestDto) {
        return this.sendPnsToTopic(notificationRequestDto);
    }

    @Override
    public GeneralApiResponse<Object> createGroupTokens(String notificationKeyName, List<String> tokens) {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfig).getInputStream())).build();

            URL url = new URL("https://fcm.googleapis.com/fcm/notification");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.connect();

            con.setRequestProperty("project_id", options.getProjectId());
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");

            JSONObject data = new JSONObject();
            data.put("operation", "tokens");
            data.put("notification_key_name", notificationKeyName);
            data.put("registration_ids",
                    new JSONArray(tokens));

            OutputStream os = con.getOutputStream();
            os.write(data.toString().getBytes("UTF-8"));
            os.close();

            InputStream is = con.getInputStream();
            String responseString =
                    new Scanner(is, "UTF-8").useDelimiter("\\A").next();
            is.close();

            JSONObject response = new JSONObject(responseString);
            var notificationKeyResult = response.getString("notification_key");
            return new GeneralApiResponse<>(StatusEnum.SUCCESS.getValue(), Constants.SUCCESS, notificationKeyResult);
        } catch (Exception e) {
            return new GeneralApiResponse<>(StatusEnum.FAILED.getValue(), Constants.FAILED);
        }
    }

    @Override
    public GeneralApiResponse<Object> addDeviceByNotificationKey(String notificationKey, String notificationKeyName, List<String> tokens) {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfig).getInputStream())).build();

            URL url = new URL("https://fcm.googleapis.com/fcm/notification");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.connect();

            con.setRequestProperty("project_id", options.getProjectId());
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");

            JSONObject data = new JSONObject();
            data.put("operation", "add");
            data.put("notification_key_name", notificationKeyName);
            data.put("notification_key", notificationKey);
            data.put("registration_ids",
                    new JSONArray(tokens));

            OutputStream os = con.getOutputStream();
            os.write(data.toString().getBytes("UTF-8"));
            os.close();

            InputStream is = con.getInputStream();
            String responseString =
                    new Scanner(is, "UTF-8").useDelimiter("\\A").next();
            is.close();

            JSONObject response = new JSONObject(responseString);
            var notificationKeyResult = response.getString("notification_key");
            return new GeneralApiResponse<>(StatusEnum.SUCCESS.getValue(), Constants.SUCCESS, notificationKeyResult);
        } catch (Exception e) {
            return new GeneralApiResponse<>(StatusEnum.FAILED.getValue(), Constants.FAILED);
        }
    }



}
