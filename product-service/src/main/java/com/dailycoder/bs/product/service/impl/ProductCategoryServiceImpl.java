package com.dailycoder.bs.product.service.impl;

import com.dailycoder.bs.product.entity.ProductCategory;
import com.dailycoder.bs.product.mapper.ProductCategoryMapper;
import com.dailycoder.bs.product.repository.ProductCategoryRepository;
import com.dailycoder.bs.product.service.ProductCategoryService;
import com.dailycoder.bs.product.vo.ProductCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductCategoryMapper productCategoryMapper;

    @Override
    public ProductCategoryVO createProductCategory(ProductCategoryVO createProductCategoryRequest) {
        ProductCategory productCategory = ProductCategory.builder()
                .productCategoryName(createProductCategoryRequest.getProductCategoryName())
                .description(createProductCategoryRequest.getDescription())
                .build();

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
        //set defaults
        if (size == null || size == 0) {
            size = 20;
        }

        //set defaults
        if (page == null || page == 0) {
            page = 0;
        }

        Pageable pageable;

        if (sort == null) {
            pageable = PageRequest.of(page, size);
        } else {
            Sort.Order order;

            try {
                String[] split = sort.split(",");

                Sort.Direction sortDirection = Sort.Direction.fromString(split[1]);
                order = new Sort.Order(sortDirection, split[0]).ignoreCase();
                pageable = PageRequest.of(page, size, Sort.by(order));

            } catch (Exception e) {
                throw new RuntimeException("Not a valid sort value, It should be 'fieldName,direction', example : 'productCategoryName,asc");
            }

        }

        return productCategoryMapper.fromPagedEntity(productCategoryRepository.findAll(pageable));
    }


}
