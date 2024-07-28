package com.products.service;

import com.products.model.Appliance;
import com.products.repository.ApplianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplianceService {

    @Autowired
    private ApplianceRepository applianceRepository;

    public List<Appliance> getAllAppliances() {
        return applianceRepository.findAll();
    }

    public Appliance getApplianceById(int id) {
        return applianceRepository.findById(id).orElse(null);
    }

    public Appliance saveAppliance(Appliance appliance) {
        return applianceRepository.save(appliance);
    }

    public void deleteAppliance(int id) {
        applianceRepository.deleteById(id);
    }
}
