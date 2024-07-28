package com.products.controller;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.products.model.ProductImage;
import com.products.model.Products;
import com.products.service.ProductImageService;
import com.products.service.ProductsService;

@RestController
@RequestMapping("/api/images")
public class ProductImageController {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private ProductImageService service;

    @Autowired
    private ProductsService productsService;

    @GetMapping
    public List<ProductImage> getAllImages() {
        return service.getAllImages();
    }

    @GetMapping("/{id}")
    public ProductImage getImageById(@PathVariable Integer id) {
        return service.getImageById(id);
    }

    @PostMapping(consumes = { "multipart/form-data" })
    public ProductImage saveImage(@RequestPart("file") MultipartFile file, @RequestPart("image") ProductImage image) throws IOException {
        String fileName = saveFile(file);
        image.setImageUrl(fileName);

        // 設置Product物件
        Products product = productsService.getProductById(image.getProductId());
        if (product != null) {
            image.setProduct(product);
        } else {
            throw new RuntimeException("Product not found with id " + image.getProductId());
        }

        return service.saveImage(image);
    }

    @PutMapping(value = "/{id}", consumes = { "multipart/form-data" })
    public ProductImage updateImage(@PathVariable Integer id, @RequestPart("file") MultipartFile file, @RequestPart("image") ProductImage image) throws IOException {
        if (!file.isEmpty()) {
            String fileName = saveFile(file);
            image.setImageUrl(fileName);
        } else {
            // 獲取現有的圖片URL以免覆蓋
            ProductImage existingImage = service.getImageById(id);
            if (existingImage != null) {
                image.setImageUrl(existingImage.getImageUrl());
            } else {
                throw new RuntimeException("Image not found with id " + id);
            }
        }

        Products product = productsService.getProductById(image.getProductId());
        if (product != null) {
            image.setProduct(product);
        } else {
            throw new RuntimeException("Product not found with id " + image.getProductId());
        }

        image.setImageId(id);
        return service.updateImage(image);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Integer id) {
        ProductImage image = service.getImageById(id);
        if (image != null) {
            service.deleteImageFile(image.getImageUrl());
            service.deleteImage(id);
        } else {
            throw new RuntimeException("Image not found with id " + id);
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (FileAlreadyExistsException e) {
            // 處理檔案已存在的情況，例如重命名檔案
            String newFileName = System.currentTimeMillis() + "_" + fileName;
            filePath = uploadPath.resolve(newFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        return "/uploads/" + filePath.getFileName().toString();
    }
}
