package com.great.fpay.mapper;

import com.great.fpay.dto.ServiceProviderResponse;
import com.great.fpay.entity.ServiceProvider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceProviderMapper {

    ServiceProviderResponse toServiceProviderResponse(ServiceProvider serviceProvider);

}
