package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api")
public class MeteoController {
	
	@Value("${api.path}")
	private String apiPath;
	@Value("${api.key}")
	private String apiKey;
	
	@RequestMapping("/")
	public String index() {
		return "IT WORKS!!";
	}
	
	@GetMapping(path = "/meteo/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getMeteoByHours(@PathVariable(required = true, name="cityName") String cityName) {
		if (cityName == null) {
			return ResponseEntity.notFound().build();
		} else {
			final String uri = this.apiPath + "/hourly?q=" + cityName + "&APPID=" + this.apiKey;
			try {			
				RestTemplate restTemplate = new RestTemplate();
				return ResponseEntity.ok(restTemplate.getForObject(uri, String.class));
			} catch(Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
	}

}