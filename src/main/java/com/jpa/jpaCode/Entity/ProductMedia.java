package com.jpa.jpaCode.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "product_media")
public class ProductMedia extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String imgPreview;

    private String imgOriginal;

    private boolean isMainImg;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Products product;

    @ManyToOne
    @JoinColumn(name="product_variant_id")
    private ProductVariants productVariants;
}
