package com.jpa.jpaCode.Service.ServiceImpl;

import com.jpa.jpaCode.Dto.ProductDto;
import com.jpa.jpaCode.model.GeneralApiResponse;
import com.jpa.jpaCode.model.PagingPattens;

public interface ProductService {

    GeneralApiResponse<Object> getProducts(PagingPattens paging);

    GeneralApiResponse<Object> getProductById(long idProduct);

    GeneralApiResponse<Object> deleteProductById(long idProduct);

    GeneralApiResponse<Object> createProduct(ProductDto productDto);

    GeneralApiResponse<Object> updateProduct(ProductDto productDto);



}
