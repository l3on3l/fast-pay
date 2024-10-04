package com.great.fpay.repository;

import com.great.fpay.entity.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {

    List<ServiceProvider> findByNameContainingIgnoreCase(String name);

    ServiceProvider findByServiceCode(String serviceCode);

}
