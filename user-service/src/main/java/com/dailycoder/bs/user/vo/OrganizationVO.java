package com.dailycoder.bs.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationVO {

    private String id;

    @NotNull(message = "Orgnization Name should not be null!")
    @NotEmpty(message = "Orgnization Name should not be empty!")
    private String name;

    @NotNull(message = "Orgnization Code should not be null!")
    @NotEmpty(message = "Orgnization Code should not be empty!")
    private String code;


    private String description;
}
