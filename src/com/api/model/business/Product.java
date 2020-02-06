package com.api.model.business;

import java.util.List;

import com.api.core.annotations.Field;

public class Product {
	
	@Field(required = true, displayAs = "upload")
	private List<String> images;
	@Field(required = true, minLength = 2, maxLength = 48, dataType = "alphanumric")
	private String brand;
	@Field(required = true, minLength = 2, maxLength = 48, dataType = "alphanumric")
	private String modelNo;
	@Field(required = true, minLength = 12, maxLength = 400, dataType = "alphanumric")
	private String title;
	@Field(required = true, minLength = 48, maxLength = 2000, dataType = "alphanumric")
	private String desc;
	private Double price;
	private Integer unitsAvailable;
	@Field(required = true, minLength = 48, maxLength = 4000, dataType = "alphanumric")
	private String specs;
	private Double ratings;
	private String tags;
	private String category;	
	
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Double getCurrentPrice() {
		return price;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.price = currentPrice;
	}
	public Integer getUnitsAvailable() {
		return unitsAvailable;
	}
	public void setUnitsAvailable(Integer unitsAvailable) {
		this.unitsAvailable = unitsAvailable;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
	public Double getRatings() {
		return ratings;
	}
	public void setRatings(Double ratings) {
		this.ratings = ratings;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
