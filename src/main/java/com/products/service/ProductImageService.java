package com.products.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.model.ProductImage;
import com.products.repository.ProductImageRepository;

@Service
public class ProductImageService {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private ProductImageRepository repository;


    public List<ProductImage> getAllImages() {
        return repository.findAll();
    }

    public ProductImage getImageById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public ProductImage saveImage(ProductImage image) {
        if (image.getProduct() == null || image.getProductId() == null) {
            throw new RuntimeException("Product cannot be null");
        }
        return repository.save(image);
    }

    public ProductImage updateImage(ProductImage image) {
        if (repository.existsById(image.getImageId())) {
            if (image.getProduct() == null || image.getProductId() == null) {
                throw new RuntimeException("Product cannot be null");
            }
            return repository.save(image);
        } else {
            throw new RuntimeException("Image not found with id " + image.getImageId());
        }
    }

    public void deleteImage(Integer id) {
        repository.deleteById(id);
    }

    public void deleteImageFile(String imageUrl) {
        // 提取檔案名稱
        String fileName = imageUrl.replaceFirst(".*/(.*)$", "$1");
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image file: " + imageUrl, e);
        }
    }
}
