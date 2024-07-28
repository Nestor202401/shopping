package com.products.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "appliances")
@PrimaryKeyJoinColumn(name = "product_id")
public class Appliance extends Products {

	@Column(name = "power", nullable = false)
    private String power;

    @Column(name = "voltage", nullable = false)
    private String voltage;

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
    
    
}
