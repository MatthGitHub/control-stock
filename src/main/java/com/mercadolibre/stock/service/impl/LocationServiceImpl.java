package com.mercadolibre.stock.service.impl;

import com.mercadolibre.stock.model.entity.Location;
import com.mercadolibre.stock.repository.LocationRepository;
import com.mercadolibre.stock.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("locationService")
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location get(String code, String depositCode) {
        return locationRepository.findByCodeAndDepositCode(code, depositCode)
                .orElse(null);
    }
}
