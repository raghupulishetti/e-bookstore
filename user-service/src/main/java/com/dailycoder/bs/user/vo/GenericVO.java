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
public class GenericVO {

    private String id;

    @NotNull(message = "Name should not be null!")
    @NotEmpty(message = "Name should not be empty!")
    private String name;

    @NotNull(message = "Code should not be null!")
    @NotEmpty(message = "Code should not be empty!")
    private String code;

    private String description;
}
