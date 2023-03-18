package com.jpa.jpaCode.model;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class OrderPagingPattens {
    private String property;
    private Sort.Direction direction;
}
