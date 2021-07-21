package com.dailycoder.bs.product.controller;

import com.dailycoder.bs.commom.http.header.HeaderGenerator;
import com.dailycoder.bs.product.service.ProductService;
import com.dailycoder.bs.product.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private HeaderGenerator headerGenerator;

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductVO productVO, HttpServletRequest request){

        ProductVO product = productService.createProduct(productVO);

        return new ResponseEntity<>(
                productVO,
                headerGenerator.getHeadersForSuccessPostMethod(request, product.getProductId()),
                HttpStatus.CREATED);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<ProductVO> getProduct(@PathVariable("productId") String productId) {

        ProductVO product = productService.getProduct(productId);
        return ResponseEntity.ok(product);

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody @Valid ProductVO productVO) {

        productService.updateProduct(productVO);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestParam(value = "sort", required = false) String sort,
                                                     @RequestParam(value = "page", required = false) Integer page,
                                                     @RequestParam(value = "size", required = false) Integer size) {

        Page<ProductVO> list = productService.getAllProducts(sort, page, size);
        return ResponseEntity.ok(list);

    }
}
