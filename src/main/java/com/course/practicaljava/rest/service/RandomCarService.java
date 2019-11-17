/**
 * 
 */
package com.course.practicaljava.rest.service;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.course.practicaljava.rest.domain.Car;
import com.course.practicaljava.rest.domain.Engine;
import com.course.practicaljava.rest.domain.Tire;
import com.course.practicaljava.util.RandomDateUtil;

/**
 * @author steph
 *
 */
@Service
public class RandomCarService implements CarService {

	private Random random = new Random();

	@Override
	public Car generateCar() {
		var randomBrand = BRANDS.get(random.nextInt(BRANDS.size()));
		var randomColor = COLORS.get(random.nextInt(COLORS.size()));
		var randomType = TYPES.get(random.nextInt(TYPES.size()));

		var car = new Car(randomBrand, randomColor, randomType);

		car.setAvailable(random.nextBoolean());
		car.setPrice(5000 + random.nextInt(7001));
		car.setFirstReleaseDate(RandomDateUtil.generateRandomDate());
		var randomCount = random.nextInt(ADDITIONAL_FEATURES.size());
		var additionalFeatures = new ArrayList<String>();
		for (int i = 1; i <= randomCount; i++) {
			additionalFeatures.add(ADDITIONAL_FEATURES.get(i - 1));
		}
		car.setAdditionalFeatures(additionalFeatures);
		// Random Engine
		var randomFuelType = FUEL_TYPES.get(random.nextInt(FUEL_TYPES.size()));
		var randomHorsePower = 100 + random.nextInt(121);
		Engine randomEngine = new Engine(randomFuelType, randomHorsePower);
		car.setEngine(randomEngine);
		
		// Tire
		var randomCompatibleTires = new ArrayList<Tire> (); 
		for (int i=0; i< 3; i++) {
			var tireManufacturer = TIRE_MANUFACTURERS.get(random.nextInt(TIRE_MANUFACTURERS.size()));
			var tireSize = 15 + random.nextInt(3);
			var tirePrice = 200 + random.nextInt(201);
			Tire randomCompatibleTire = new Tire(tireManufacturer, tireSize, tirePrice);
			randomCompatibleTires.add(randomCompatibleTire);
		}
		car.setCompatibleTires(randomCompatibleTires);
		
		if (random.nextBoolean()) {
			car.setSecretFeature("Can fly");
		}
		return car;
	}

}
