package com.jpa.jpaCode.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name  ="product_sold")
public class ProductsSold extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int sold;

    private int remain;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_variant_id", referencedColumnName = "id")
    private ProductVariants productVariants;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Products products;

}
