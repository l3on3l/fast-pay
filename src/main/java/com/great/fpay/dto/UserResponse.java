package com.great.fpay.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private BigDecimal balance;

}
