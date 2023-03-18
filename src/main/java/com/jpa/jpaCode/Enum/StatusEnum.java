package com.jpa.jpaCode.Enum;

import lombok.Data;


public enum StatusEnum {

    SUCCESS(0), FAILED(1);

    private int value;

    StatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
