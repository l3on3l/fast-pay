package com.great.fpay.service.serviceProvider;

import com.great.fpay.dto.ServiceProviderResponse;

import java.util.List;

public interface IServiceProviderService {

    ServiceProviderResponse findById(Long id);

    List<ServiceProviderResponse> findAll();

    List<ServiceProviderResponse> findByName(String name);

    void deleteById(Long id);

}
