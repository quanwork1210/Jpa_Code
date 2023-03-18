package com.jpa.jpaCode.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductOption {
    private String name;
    private int quantity;
    private double price;
    private List<ProductMedia> medias;
}
