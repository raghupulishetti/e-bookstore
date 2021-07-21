package com.dailycoder.bs.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {

    private String productId;
    private String productName;
    private String description;
    private double price;
    private String productCategory;
    private int availableItemCount;
    private String imageId;
}
