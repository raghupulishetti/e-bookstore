package com.dailycoder.bs.product.service.impl;

import com.dailycoder.bs.commom.util.PagingUtil;
import com.dailycoder.bs.product.entity.Product;
import com.dailycoder.bs.product.entity.ProductCategory;
import com.dailycoder.bs.product.mapper.ProductMapper;
import com.dailycoder.bs.product.repository.ProductCategoryRepository;
import com.dailycoder.bs.product.repository.ProductRepository;
import com.dailycoder.bs.product.service.ProductService;
import com.dailycoder.bs.product.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductMapper productMapper;

    @Override
    public ProductVO createProduct(ProductVO productVO) {
        Optional<ProductCategory> productCategoryOptional =
                productCategoryRepository.findById(productVO.getProductCategoryId());

        ProductCategory productCategory = productCategoryOptional.orElseThrow(() -> new RuntimeException("ProductCategory doesn't exist!"));
        Product product = productMapper.toEntity(productVO,productCategory);
        Product savedProduct = productRepository.save(product);
        return productMapper.fromEntity(savedProduct);
    }

    @Override
    public ProductVO getProduct(String productId) {
        Optional<Product> productOptional =
                productRepository.findById(productId);
        Product product = productOptional.orElseThrow(() -> new RuntimeException("Product doesn't exist with the given Id: " + productId));
        return productMapper.fromEntity(product);
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public ProductVO updateProduct(ProductVO updateProductRequest) {
        String productId = updateProductRequest.getProductId();
        Optional<Product> productOptional =
                productRepository.findById(productId);
        Product product = productOptional.orElseThrow(() -> new RuntimeException("Product doesn't exist with the given Id: " + productId));
        return productMapper.fromEntity(product);
    }

    @Override
    public Page<ProductVO> getAllProducts(String sort, Integer page, Integer size) {

        Pageable pageable = PagingUtil.getPageable(sort, page, size);
        return productMapper.fromPagedEntity(productRepository.findAll(pageable));
    }
}
