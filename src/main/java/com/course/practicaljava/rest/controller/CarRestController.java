/**
 * 
 */
package com.course.practicaljava.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.practicaljava.exception.IllegalApiParamException;
import com.course.practicaljava.repository.CarElasticRepository;
import com.course.practicaljava.rest.domain.Car;
import com.course.practicaljava.rest.domain.ErrorResponse;
import com.course.practicaljava.rest.service.CarService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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

	@ApiOperation(value = "Echo car from request body")
	@PostMapping(path = "/echo", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String echo(@ApiParam(value = "Car for echo") @RequestBody Car car) {
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

	@ApiOperation(value = "Find car by ID")
	@GetMapping(path = "/cars/{id}")
	public Car findCarsById(@ApiParam(value = "ID of car") @PathVariable String id) {
		return carRepository.findById(id).orElse(null);
	}

	@PutMapping(path = "/cars/{id}")
	public Car updateCarById(@PathVariable String id, @RequestBody Car updatedCar) {
		updatedCar.setId(id);
		return carRepository.save(updatedCar);
	}

	@GetMapping(path = "/cars/{brand}/{color}")
	@ApiResponses({
			@ApiResponse(code = 400, message = "Invalid parameter (numeric color)", response = ErrorResponse.class),
			@ApiResponse(code = 200, message = "Return cars with specific brand and color", response = Car.class, responseContainer = "List") })
	public ResponseEntity<Object> findCarsByPath(@PathVariable String brand, @PathVariable String color,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		var pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, "price"));
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.SERVER, "Spring");
		headers.add("Custom", "Custom");
		if (StringUtils.isNumeric(color)) {
			var errorResponse = new ErrorResponse("Invalid Color", System.currentTimeMillis());
			return new ResponseEntity<>(errorResponse, headers, HttpStatus.BAD_REQUEST);
		}
		var cars = carRepository.findByBrandAndColor(brand, color, pageable).getContent();

		return ResponseEntity.ok().headers(headers).body(cars);
	}

	@GetMapping(path = "/cars")
	public List<Car> findCarsByParam(@RequestParam String brand, @RequestParam String color,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		if (StringUtils.isNumeric(color)) {
			throw new IllegalArgumentException("Invalid Color  : " + color);
		}
		if (StringUtils.isNumeric(brand)) {
			throw new IllegalApiParamException("Invalid brand : " + brand);
		}

		var pageable = PageRequest.of(page, size);
		return carRepository.findByBrandAndColor(brand, color, pageable).getContent();
	}

	@GetMapping(path = "/cars/date")
	public List<Car> findCarsReleasedAfter(
			@RequestParam(name = "first_released_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date firstReleasedDate) {
		return carRepository.findByFirstReleaseDateAfter(firstReleasedDate.getTime());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleInvalidColorsException(IllegalArgumentException ie) {
		var errorMessages = "Exception : " + ie.getMessage();
		logger.error(errorMessages);
		var errorResponse = new ErrorResponse(errorMessages, System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, null, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(IllegalApiParamException.class)
	public ResponseEntity<ErrorResponse> handleApiParamException(IllegalApiParamException ie) {
		var errorMessages = "Exception : " + ie.getMessage();
		logger.error(errorMessages);
		var errorResponse = new ErrorResponse(errorMessages, System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, null, HttpStatus.BAD_REQUEST);

	}

}
