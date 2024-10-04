package com.great.fpay.service.serviceProvider;

import com.great.fpay.dto.ServiceProviderResponse;
import com.great.fpay.exceptions.ServiceProviderException;
import com.great.fpay.exceptions.UserException;
import com.great.fpay.mapper.ServiceProviderMapper;
import com.great.fpay.repository.ServiceProviderRepository;
import com.great.fpay.utils.ErrorCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceProviderService implements IServiceProviderService {

    private final ServiceProviderRepository serviceProviderRepository;

    private final ServiceProviderMapper serviceProviderMapper;

    @Override
    public ServiceProviderResponse findById(Long id) {
        return serviceProviderRepository.findById(id)
                .map(serviceProviderMapper::toServiceProviderResponse)
                .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, ErrorCatalog.SERVICE_PROVIDER_NOT_FOUND));
    }

    @Override
    public List<ServiceProviderResponse> findAll() {
        return serviceProviderRepository.findAll()
                .stream()
                .map(serviceProviderMapper::toServiceProviderResponse)
                .toList();
    }

    @Override
    public List<ServiceProviderResponse> findByName(String name) {
        return serviceProviderRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(serviceProviderMapper::toServiceProviderResponse)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        if (serviceProviderRepository.findById(id).isEmpty()) {
            throw new UserException(HttpStatus.NOT_FOUND, ErrorCatalog.SERVICE_PROVIDER_NOT_FOUND);
        }

        serviceProviderRepository.deleteById(id);
    }
}
