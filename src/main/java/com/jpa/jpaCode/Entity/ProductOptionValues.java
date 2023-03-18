package com.jpa.jpaCode.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product_option_values")
public class ProductOptionValues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String value;

    @ManyToOne
    @JoinColumn(name="product_option_id")
    private ProductOptions options;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Products products;

    private int numericalOrder;
}
