package com.article.service.mvc.start_up.service;

import com.article.service.mvc.start_up.entity.StartUpChart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StartUpChartRepository extends MongoRepository<StartUpChart,String> {

}
