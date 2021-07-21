package com.dailycoder.bs.product.service;

import com.dailycoder.bs.product.vo.ProductCategoryVO;
import com.dailycoder.bs.product.vo.ProductVO;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductVO createProduct(ProductVO productVO);

    ProductVO getProduct(String productId);

    void deleteProduct(String productId);

    ProductVO updateProduct(ProductVO productVO);

    Page<ProductVO> getAllProducts(String sort, Integer page, Integer size);
}
