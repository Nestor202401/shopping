package com.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.model.Electronics;
import com.products.service.ElectronicsService;

@RestController
@RequestMapping("/api/electronics")
public class ElectronicsController {

    @Autowired
    private ElectronicsService electronicsService;

    @GetMapping
    public List<Electronics> getAllAppliances() {
        return electronicsService.getAllElectronics();
    }

    @GetMapping("/{id}")
    public Electronics getElectronicsById(@PathVariable int id) {
        return electronicsService.getElectronicsById(id);
    }

    @PostMapping
    public Electronics createElectronics(@RequestBody Electronics electronics) {
        return electronicsService.saveElectronics(electronics);
    }

    @PutMapping("/{id}")
    public Electronics updateElectronics(@PathVariable int id, @RequestBody Electronics electronics) {
    	electronics.setProductId(id);
        return electronicsService.saveElectronics(electronics);
    }

    @DeleteMapping("/{id}")
    public void deleteAppliance(@PathVariable int id) {
    	electronicsService.deleteElectronics(id);
    }
}
