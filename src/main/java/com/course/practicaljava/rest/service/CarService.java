/**
 * 
 */
package com.course.practicaljava.rest.service;

import java.util.List;

import com.course.practicaljava.rest.domain.Car;

/**
 * @author steph
 *
 */
public interface CarService {

	List<String> BRANDS = List.of ("Toyota","Honda", "Ford");
	List<String> COLORS = List.of("Red", "Black", "White");
	List <String> TYPES = List.of("Sedan", "SUV", "MPV");
	
	Car generateCar ();
}
