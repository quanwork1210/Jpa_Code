package com.jpa.jpaCode.Entity;

import com.jpa.jpaCode.Enum.ProductTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Products extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "product")
    private List<ProductMedia> medias;

    @OneToMany(mappedBy = "products")
    private List<ProductOptions> options;

    private ProductTypeEnum type;

    private boolean isEnabled;

    @OneToOne(mappedBy = "products")
    private ProductVariants productVariant;

    @OneToOne(mappedBy = "products")
    private ProductsSold sold;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductCategories> categories;

    @OneToMany(mappedBy = "products")
    private List<ProductOptionValues> optionValues;
}
