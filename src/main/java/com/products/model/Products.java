package com.products.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract  class Products {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "product_id")
	    private int productId;
	    
	    @Column(name = "name", nullable = false)
	    private String name;
	    
	    @Column(name = "brand", nullable = false)
	    private String brand;
	    
	    @Column(name = "price", nullable = false)
	    private int price;
	    
	    @Column(name = "category", nullable = false)
	    private String category;
	    
	    @Column(name = "stock", nullable = false)
	    private int stock;
	    
	    @Column(name = "description", columnDefinition = "TEXT") 
	    private String description;
	    
	    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	    @JsonManagedReference
	    private List<ProductImage> productImages;

		public int getProductId() {
			return productId;
		}

		public void setProductId(int productId) {
			this.productId = productId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public List<ProductImage> getProductImages() {
			return productImages;
		}

		public void setProductImages(List<ProductImage> productImages) {
			this.productImages = productImages;
		}
	    
	    
    
    
}
