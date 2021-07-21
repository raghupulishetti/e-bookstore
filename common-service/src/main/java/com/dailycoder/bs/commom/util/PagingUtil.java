package com.dailycoder.bs.commom.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PagingUtil {

    public static Pageable getPageable(String sort, Integer page, Integer size) {
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
        return pageable;
    }
}
