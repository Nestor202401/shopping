package com.products.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "electronics")
@PrimaryKeyJoinColumn(name = "product_id")
public class Electronics extends Products {
	
	@Column(name = "color", nullable = false)
	private String color;
	
    @Column(name = "specifications", columnDefinition = "TEXT", nullable = false)
	private  String specifications;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
    
    
}
