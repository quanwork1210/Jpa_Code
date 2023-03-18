package com.jpa.jpaCode.Service.ServiceImpl;

import com.jpa.jpaCode.Constants.Constants;
import com.jpa.jpaCode.Dto.ProductDto;
import com.jpa.jpaCode.Entity.Products;
import com.jpa.jpaCode.Enum.ProductTypeEnum;
import com.jpa.jpaCode.Enum.StatusEnum;
import com.jpa.jpaCode.Repository.ProductRepository;
import com.jpa.jpaCode.model.GeneralApiResponse;
import com.jpa.jpaCode.model.PagingPattens;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public GeneralApiResponse<Object> getProducts(PagingPattens paging) {
        return null;
    }

    @Override
    public GeneralApiResponse<Object> getProductById(long idProduct) {
        return null;
    }

    @Override
    public GeneralApiResponse<Object> deleteProductById(long idProduct) {
        return null;
    }

    @Override
    public GeneralApiResponse<Object> createProduct(ProductDto productDto) {
       var resultValidate = validateProduct(productDto);
        if (!resultValidate.isEmpty()) {
           return new GeneralApiResponse(StatusEnum.FAILED.getValue(), Constants.FAILED);
       }
        if (ProductTypeEnum.SIMPLE.equals(productDto.getType())) {



        } else if (ProductTypeEnum.VARIANT.equals(productDto.getType())) {

            //Upload image

            //Save product
            Products products = new Products();

            //Save quantity

            //Save option and option value

        }


        return null;
    }


    private HashMap<String, String> validateProduct(ProductDto productDto) {
        log.info("Start validate product variant");
        HashMap<String, String> error = new HashMap<>();
        if (productDto.getName().isEmpty()) {
           error.put("name", "Name product is not empty");
        }

        if (productDto.getMainImage().isEmpty() || productDto.getImgOriginal().isEmpty()) {
            error.put("mainImage", "Main image is not empty");
        }

        if (productDto.getOptions().size() == 0) {
            error.put("options", "Product variant not have option");
        }

        if (ProductTypeEnum.VARIANT.equals(productDto.getType())) {
            productDto.getOptions().forEach(option -> {
                if (option.getName().isEmpty()) {
                    error.put("optionName", "Option name is not empty");
                }
                if (option.getPrice() < 0) {
                    error.put("optionPrice", "Option price incorrect");
                }
            });
        } else {
            // TODO validate Attribute


        }



        return error;
    }


    @Override
    public GeneralApiResponse<Object> updateProduct(ProductDto productDto) {
        return null;
    }
}
