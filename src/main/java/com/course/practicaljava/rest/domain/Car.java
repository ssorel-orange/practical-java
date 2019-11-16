/**
 * 
 */
package com.course.practicaljava.rest.domain;

import java.util.Date;

/**
 * @author steph
 *
 */
public class Car {

	private String brand;

	private String color;

	private String type;

	private int price;

	private boolean available;

	private Date firstReleaseDate;

	public Car() {
		super();
	}

	public Car(String brand, String color, String type) {
		super();
		this.brand = brand;
		this.color = color;
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
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

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", color=" + color + ", type=" + type + ", price=" + price + ", available="
				+ available + ", firstReleaseDate=" + firstReleaseDate + "]";
	}

	

}