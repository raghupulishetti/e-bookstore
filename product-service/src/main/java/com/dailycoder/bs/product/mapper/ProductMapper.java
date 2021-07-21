package com.dailycoder.bs.product.mapper;

import com.dailycoder.bs.product.entity.Product;
import com.dailycoder.bs.product.entity.ProductCategory;
import com.dailycoder.bs.product.vo.ProductVO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductVO fromEntity(Product product) {
        return ProductVO.builder().productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .availableItemCount(product.getAvailableItemCount())
                .imageId(product.getImageId())
                .productCategoryId(product.getProductCategory().getProductCategoryId())
                .productCategoryName(product.getProductCategory().getProductCategoryName())
                .build();
    }

    public Product toEntity(ProductVO productVO, ProductCategory productCategory) {
        return Product.builder()
                .productName(productVO.getProductName())
                .description(productVO.getDescription())
                .availableItemCount(productVO.getAvailableItemCount())
                .price(productVO.getPrice())
                .productCategory(productCategory)
                .imageId(productVO.getImageId())
                .build();
    }

    public Page<ProductVO> fromPagedEntity(Page<Product> entities) {
        return entities.map(this::fromEntity);
    }
}
