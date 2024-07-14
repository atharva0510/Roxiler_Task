package com.jspiders.roxilertask.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = false, nullable = false)
	private String title;
	
	@Column(unique = false, nullable = false)
	private double price;
	
	@Column(unique = false, nullable = false)
	private String description;
	
	@Column(unique = false, nullable = false)
	private String category;
	
	@Column(unique = false, nullable = false)
	private String image;
	
	@Column(unique = false, nullable = false)
	private boolean sold;
	
	@Column(unique = false, nullable = false)
	private String dateOfSale;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public String getDateOfSale() {
		return dateOfSale;
	}

	public void setDateOfSale(String dateOfSale) {
		this.dateOfSale = dateOfSale;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", price=" + price + ", description=" + description
				+ ", category=" + category + ", image=" + image + ", sold=" + sold + ", dateOfSale=" + dateOfSale + "]";
	}
	
	

}
