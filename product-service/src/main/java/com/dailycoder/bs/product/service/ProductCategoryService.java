package com.dailycoder.bs.product.service;

import com.dailycoder.bs.product.entity.ProductCategory;
import com.dailycoder.bs.product.vo.ProductCategoryVO;
import org.springframework.data.domain.Page;

public interface ProductCategoryService {
    ProductCategoryVO createProductCategory(ProductCategoryVO createProductCategoryRequest);

    ProductCategoryVO getProductCategory(String productCategoryId);

    void deleteProductCategory(String productCategoryId);

    ProductCategoryVO updateProductCategory(ProductCategoryVO updateProductCategoryRequest);

    Page<ProductCategoryVO> getAllProductCategories(String sort, Integer page, Integer size);
}
