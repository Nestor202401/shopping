package com.products.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.model.Electronics;
import com.products.repository.ElectronicsRepository;

@Service
public class ElectronicsService {

    @Autowired
    private ElectronicsRepository electronicsRepository;

    public List<Electronics> getAllElectronics() {
        return electronicsRepository.findAll();
    }

    public Electronics getElectronicsById(int id) {
        return electronicsRepository.findById(id).orElse(null);
    }

    public Electronics saveElectronics(Electronics electronics) {
        return electronicsRepository.save(electronics);
    }

    public void deleteElectronics(int id) {
    	electronicsRepository.deleteById(id);
    }
}
