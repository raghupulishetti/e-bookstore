package com.dailycoder.bs.product.service.impl;

import com.dailycoder.bs.commom.util.PagingUtil;
import com.dailycoder.bs.product.entity.ProductCategory;
import com.dailycoder.bs.product.mapper.ProductCategoryMapper;
import com.dailycoder.bs.product.repository.ProductCategoryRepository;
import com.dailycoder.bs.product.service.ProductCategoryService;
import com.dailycoder.bs.product.vo.ProductCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductCategoryMapper productCategoryMapper;

    @Override
    public ProductCategoryVO createProductCategory(ProductCategoryVO productCategoryVO) {
        ProductCategory productCategory = productCategoryMapper.toEntity(productCategoryVO);

        ProductCategory savedProductCategory = productCategoryRepository.save(productCategory);
        return productCategoryMapper.fromEntity(savedProductCategory);
    }

    @Override
    public ProductCategoryVO getProductCategory(String productCategoryId) {
        Optional<ProductCategory> productOptional =
                productCategoryRepository.findById(productCategoryId);
        ProductCategory productCategory = productOptional.orElseThrow(() -> new RuntimeException("Product Category doesn't exist with the given Id: " + productCategoryId));
        return productCategoryMapper.fromEntity(productCategory);
    }

    @Override
    public void deleteProductCategory(String productCategoryId) {
        productCategoryRepository.deleteById(productCategoryId);
    }

    @Override
    public ProductCategoryVO updateProductCategory(ProductCategoryVO updateProductCategoryRequest) {
        String productCategoryId = updateProductCategoryRequest.getProductCategoryId();
        Optional<ProductCategory> productOptional =
                productCategoryRepository.findById(productCategoryId);
        ProductCategory productCategory = productOptional.orElseThrow(() -> new RuntimeException("Product Category doesn't exist with the given Id: " + productCategoryId));
        return productCategoryMapper.fromEntity(productCategory);
    }

    @Override
    public Page<ProductCategoryVO> getAllProductCategories(String sort, Integer page, Integer size) {

        Pageable pageable = PagingUtil.getPageable(sort, page, size);

        return productCategoryMapper.fromPagedEntity(productCategoryRepository.findAll(pageable));
    }


}
