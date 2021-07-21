package com.dailycoder.bs.product.mapper;

import com.dailycoder.bs.product.entity.ProductCategory;
import com.dailycoder.bs.product.vo.ProductCategoryVO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductCategoryMapper {

    @Autowired
    ObjectMapper objectMapper;

    public ProductCategoryVO fromEntity(ProductCategory productCategory) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.convertValue(productCategory, ProductCategoryVO.class);
    }

    public Page<ProductCategoryVO> fromPagedEntity(Page<ProductCategory> entities) {
        Page<ProductCategoryVO> dtoPage = entities.map(new Function<ProductCategory, ProductCategoryVO>() {
            @Override
            public ProductCategoryVO apply(ProductCategory entity) {
                return fromEntity(entity);
            }
        });
        return dtoPage;
    }
}
