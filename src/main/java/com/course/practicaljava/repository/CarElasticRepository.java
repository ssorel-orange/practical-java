/**
 * 
 */
package com.course.practicaljava.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.course.practicaljava.rest.domain.Car;

/**
 * @author steph
 *
 */
@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car,String>{

	public List<Car> findByBrandAndColor(String brand, String color);
	
	
	
}
