package org.manas.eclipselink.controller;

import org.manas.eclipselink.entity.City;
import org.manas.eclipselink.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

	@Autowired
	private CityService cityService;

	@RequestMapping("/")
	@ResponseBody
	@Transactional(readOnly = true)
	public String helloWorld() {
		return cityService.getCity("Bath", "UK").getName();
	}
	
	@RequestMapping("/insertCity")
	@ResponseBody
	@Transactional(readOnly = true)
	public City insertUser() {
		City city = new City("Hyderabad","India","Telengana", "17.20N 78.30E");
		return cityService.insertCity(city);
	}

}
