/**
 * 
 */
package com.course.practicaljava.rest.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author steph
 *
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Car {

	private String brand;

	private String color;

	private String type;
   
	private int price;

	private boolean available;
	@JsonFormat(pattern = "dd-MMM-yyyy hh:mm:ssaZ", timezone = "Asia/Jakarta")
	private Date firstReleaseDate;
	@JsonInclude(value = Include.NON_EMPTY)
	private List<String> additionalFeatures;
    @JsonUnwrapped
	private Engine engine;

	private List<Tire> compatibleTires;
	@JsonInclude(value = Include.NON_EMPTY)
	private String secretFeature;

	public Car() {
		super();
	}

	public Car(String brand, String color, String type) {
		super();
		this.brand = brand;
		this.color = color;
		this.type = type;
	}

	/**
	 * @return the additionalFeatures
	 */
	public List<String> getAdditionalFeatures() {
		return additionalFeatures;
	}

	public String getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
	}

	/**
	 * @return the compatibleTires
	 */
	public List<Tire> getCompatibleTires() {
		return compatibleTires;
	}

	/**
	 * @return the engine
	 */
	public Engine getEngine() {
		return engine;
	}

	/**
	 * @return the firstReleaseDate
	 */
	public Date getFirstReleaseDate() {
		return firstReleaseDate;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return the secretFeature
	 */
	public String getSecretFeature() {
		return secretFeature;
	}

	public String getType() {
		return type;
	}

	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param additionalFeatures the additionalFeatures to set
	 */
	public void setAdditionalFeatures(List<String> additionalFeatures) {
		this.additionalFeatures = additionalFeatures;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @param compatibleTires the compatibleTires to set
	 */
	public void setCompatibleTires(List<Tire> compatibleTires) {
		this.compatibleTires = compatibleTires;
	}

	/**
	 * @param engine the engine to set
	 */
	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	/**
	 * @param firstReleaseDate the firstReleaseDate to set
	 */
	public void setFirstReleaseDate(Date firstReleaseDate) {
		this.firstReleaseDate = firstReleaseDate;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @param secretFeature the secretFeature to set
	 */
	public void setSecretFeature(String secretFeature) {
		this.secretFeature = secretFeature;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", color=" + color + ", type=" + type + ", price=" + price + ", available="
				+ available + ", firstReleaseDate=" + firstReleaseDate + ", additionalFeatures=" + additionalFeatures
				+ ", engine=" + engine + ", compatibleTires=" + compatibleTires + ", secretFeature=" + secretFeature
				+ "]";
	}

}
