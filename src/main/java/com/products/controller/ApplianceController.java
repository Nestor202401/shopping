package com.products.controller;

import com.products.model.Appliance;
import com.products.service.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appliances")
public class ApplianceController {

    @Autowired
    private ApplianceService applianceService;

    @GetMapping
    public List<Appliance> getAllAppliances() {
        return applianceService.getAllAppliances();
    }

    @GetMapping("/{id}")
    public Appliance getApplianceById(@PathVariable int id) {
        return applianceService.getApplianceById(id);
    }

    @PostMapping
    public Appliance createAppliance(@RequestBody Appliance appliance) {
        return applianceService.saveAppliance(appliance);
    }

    @PutMapping("/{id}")
    public Appliance updateAppliance(@PathVariable int id, @RequestBody Appliance appliance) {
        appliance.setProductId(id);
        return applianceService.saveAppliance(appliance);
    }

    @DeleteMapping("/{id}")
    public void deleteAppliance(@PathVariable int id) {
        applianceService.deleteAppliance(id);
    }
}
