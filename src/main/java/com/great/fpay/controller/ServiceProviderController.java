package com.great.fpay.controller;

import com.great.fpay.dto.ServiceProviderResponse;
import com.great.fpay.service.serviceProvider.IServiceProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/services")
public class ServiceProviderController {

    private final IServiceProviderService serviceProviderService;

    @GetMapping("/{id}")
    public ServiceProviderResponse findById(@PathVariable Long id) {
        return serviceProviderService.findById(id);
    }

    @GetMapping
    public List<ServiceProviderResponse> findAll() {
        return serviceProviderService.findAll();
    }

    @GetMapping("/search")
    public List<ServiceProviderResponse> findByName(@RequestParam String query) {
        return serviceProviderService.findByName(query);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        serviceProviderService.deleteById(id);
    }
}
