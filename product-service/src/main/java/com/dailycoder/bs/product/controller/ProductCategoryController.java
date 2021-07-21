package com.dailycoder.bs.product.controller;

import com.dailycoder.bs.commom.http.header.HeaderGenerator;
import com.dailycoder.bs.product.service.ProductCategoryService;
import com.dailycoder.bs.product.vo.ProductCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/productcategories")
public class ProductCategoryController {

    @Autowired
    private HeaderGenerator headerGenerator;

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<?> createProductCategory(@RequestBody @Valid ProductCategoryVO createProductCategoryRequest, HttpServletRequest request) {


        ProductCategoryVO productCategoryVO = productCategoryService.createProductCategory(createProductCategoryRequest);

        return new ResponseEntity<ProductCategoryVO>(
                productCategoryVO,
                headerGenerator.getHeadersForSuccessPostMethod(request, productCategoryVO.getProductCategoryId()),
                HttpStatus.CREATED);

    }

    @GetMapping("/{productCategoryId}")
    public ResponseEntity<ProductCategoryVO> getProductCategory(@PathVariable("productCategoryId") String productCategoryId) {

        ProductCategoryVO productCategory = productCategoryService.getProductCategory(productCategoryId);
        return ResponseEntity.ok(productCategory);

    }

    @DeleteMapping("/{productCategoryId}")
    public ResponseEntity<?> deleteProductCategory(@PathVariable("productCategoryId") String productCategoryId) {
        productCategoryService.deleteProductCategory(productCategoryId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateProductCategory(@RequestBody @Valid ProductCategoryVO updateProductCategoryRequest) {

        productCategoryService.updateProductCategory(updateProductCategoryRequest);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllProductCategories(@RequestParam(value = "sort", required = false) String sort,
                                                     @RequestParam(value = "page", required = false) Integer page,
                                                     @RequestParam(value = "size", required = false) Integer size) {

        Page<ProductCategoryVO> list = productCategoryService.getAllProductCategories(sort, page, size);



        return ResponseEntity.ok(list);

    }

}
