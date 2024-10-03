package com.great.fpay.dto;

import com.great.fpay.entity.ServiceType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ServiceProviderResponse {

    private Long id;

    private String name;

    private ServiceType type;

    private String serviceCode;
}
