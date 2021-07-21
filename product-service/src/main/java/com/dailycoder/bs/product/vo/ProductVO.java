package com.dailycoder.bs.product.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVO {

    private String productId;

    @NotNull(message = "productName should not be null!")
    @NotEmpty(message = "productName should not be empty!")
    private String productName;
    private String description;
    @Min(value = 0)
    private double price;
    private int availableItemCount;
    private String imageId;

    @NotNull(message = "productCategoryId should not be null!")
    @NotEmpty(message = "productCategoryId should not be empty!")
    private String productCategoryId;
    private String productCategoryName;
}
