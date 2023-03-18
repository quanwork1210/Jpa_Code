package com.jpa.jpaCode.Dto;

import com.jpa.jpaCode.Enum.ProductTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDto {

    private long id;

    private String name;

    private String mainImage;

    private String ImgOriginal;

    private List<ProductMedia> medias;

    private List<ProductOption> options;

    private ProductTypeEnum type;

}
