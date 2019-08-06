package com.common.mvc.start_up.service;


import com.common.mvc.start_up.entity.StartUpChart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StartUpChartRepository extends MongoRepository<StartUpChart,String> {

}
