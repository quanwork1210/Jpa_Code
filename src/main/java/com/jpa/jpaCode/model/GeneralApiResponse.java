package com.jpa.jpaCode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralApiResponse <T> {

    private int status;
    private String statusCode;
    private T result;

    public GeneralApiResponse(int status, String statusCode) {
        this.status = status;
        this.statusCode = statusCode;
    }

}
