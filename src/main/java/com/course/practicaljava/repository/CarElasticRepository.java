/**
 * 
 */
package com.course.practicaljava.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.course.practicaljava.rest.domain.Car;

/**
 * @author steph
 *
 */
@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car,String>{

	public Page<Car> findByBrandAndColor(String brand, String color, Pageable pageable);
	
	public List<Car> findByFirstReleaseDateAfter (long date);
	
}
