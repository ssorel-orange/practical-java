/**
 * 
 */
package com.course.practicaljava.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.practicaljava.repository.CarElasticRepository;
import com.course.practicaljava.rest.domain.Car;
import com.course.practicaljava.rest.service.CarService;

/**
 * @author steph
 *
 */
@RestController
@RequestMapping("/api/car/v1")
public class CarRestController {
	@Autowired
	private CarService carService;
	@Autowired
	private CarElasticRepository carRepository;

	private Random random = new Random();

	private Logger logger = LoggerFactory.getLogger(CarRestController.class);

	@GetMapping(path = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
	public Car random() {
		return carService.generateCar();

	}

	@PostMapping(path = "/echo", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String echo(@RequestBody Car car) {
		logger.info("The car is " + car);
		return car.toString();
	}

	@GetMapping(path = "/random-cars", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Car> randomCars() {
		var resultList = new ArrayList<Car>();

		for (int i = 0; i < random.nextInt(6); i++) {
			resultList.add(carService.generateCar());
		}
		return resultList;
	}

	@GetMapping(path = "/cars/count", produces = MediaType.APPLICATION_JSON_VALUE)
	public long countCar() {
		return carRepository.count();

	}

	@PostMapping(path = "/cars", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Car createCar(@RequestBody Car car) {
		var newCar = carRepository.save(car);
		return newCar;
	}

	@GetMapping(path = "/cars/{id}")
	public Car findCarsById(@PathVariable String id) {
		return carRepository.findById(id).orElse(null);
	}

	@PutMapping(path = "/cars/{id}")
	public Car updateCarById(@PathVariable String id, @RequestBody Car updatedCar) {
		updatedCar.setId(id);
		return carRepository.save(updatedCar);
	}

	@GetMapping(path = "/cars/{brand}/{color}")
	public List<Car> findCarsByPath(@PathVariable String brand, @PathVariable String color) {
		return carRepository.findByBrandAndColor(brand, color);
	}

	@GetMapping(path = "/cars")
	public List<Car> findCarsByParam(@RequestParam String brand, @RequestParam String color) {
		return carRepository.findByBrandAndColor(brand, color);
	}
	
	@GetMapping(path ="/cars/date")
	public List<Car> findCarsReleasedAfter (@RequestParam (name="first_released_date") @DateTimeFormat (pattern ="yyyy-MM-dd") Date firstReleasedDate){
		return carRepository.findByFirstReleaseDateAfter(firstReleasedDate.getTime());
	}
}
