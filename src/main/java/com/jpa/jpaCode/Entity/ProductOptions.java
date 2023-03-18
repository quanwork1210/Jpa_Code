package com.jpa.jpaCode.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product_options")
public class ProductOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Products products;

    @ManyToOne
    @JoinColumn(name="product_variant_id")
    private ProductVariants variants;

    private int numericalOrder;

    @OneToMany(mappedBy = "options")
    private List<ProductOptionValues> optionValues;
}
