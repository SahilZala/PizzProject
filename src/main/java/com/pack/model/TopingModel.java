package com.pack.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="toping")
public class TopingModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String topingName;
	float price;
	public TopingModel(String topingName, float price) {
		super();
		this.topingName = topingName;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTopingName() {
		return topingName;
	}
	public void setTopingName(String topingName) {
		this.topingName = topingName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public TopingModel() {
		super();
	}
	@Override
	public String toString() {
		return "TopingModel [id=" + id + ", topingName=" + topingName + ", price=" + price + "]";
	}

	
	
}
