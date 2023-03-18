package com.jpa.jpaCode.model;

import lombok.Data;

import java.util.List;

@Data
public class SubscriptionRequestDto {

    String topicName;
    List<String> tokens;

}
