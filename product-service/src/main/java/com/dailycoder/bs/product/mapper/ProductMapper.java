package com.dailycoder.bs.product.mapper;

import com.dailycoder.bs.product.entity.Product;
import com.dailycoder.bs.product.vo.ProductVO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductVO fromEntity(Product product) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.convertValue(product, ProductVO.class);
    }
}
