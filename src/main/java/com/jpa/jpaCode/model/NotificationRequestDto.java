package com.jpa.jpaCode.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationRequestDto {
    private String topic;
    private String target;
    private String title;
    private String body;
}
