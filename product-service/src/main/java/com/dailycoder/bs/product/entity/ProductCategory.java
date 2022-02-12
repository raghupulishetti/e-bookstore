package com.dailycoder.bs.product.entity;

import com.dailycoder.bs.common.entity.DateAudit;
import com.dailycoder.bs.product.vo.ProductCategoryVO;
import com.dailycoder.bs.product.vo.ProductVO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_CATEGORY")
@Builder
public class ProductCategory extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "PRODUCT_CATEGORY_ID", updatable = false, nullable = false)
    private String productCategoryId;

    @Column(name = "PRODUCT_CATEGORY_NAME", nullable = false)
    private String productCategoryName;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>(0);

    private String description;


}
