package com.jpa.jpaCode.Controller;

import com.jpa.jpaCode.Dto.ProductDto;
import com.jpa.jpaCode.model.GeneralApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/product")
public class ProductController {

    @PostMapping
    GeneralApiResponse<Object> createProduct(@RequestParam ProductDto productDto) {
        return null;
    }

}
