package com.jpa.jpaCode.model;

import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PagingPattens {
    private int index;
    private int size;
    private List<OrderPagingPattens> orders;

    public List<Sort.Order> getSortOrders() {
        return orders.stream().map(
                order -> {return new Sort.Order(order.getDirection(), order.getProperty()); }
        ).collect(Collectors.toList());
    }

}
