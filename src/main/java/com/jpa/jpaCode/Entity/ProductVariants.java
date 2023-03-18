package com.jpa.jpaCode.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product_variants")
public class ProductVariants extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Products products;

    @OneToMany(mappedBy = "productVariants")
    private List<ProductMedia> medias;

    @OneToMany(mappedBy = "variants")
    private List<ProductOptions> options;

    @OneToOne(mappedBy = "productVariants")
    private ProductsSold sold;

}
