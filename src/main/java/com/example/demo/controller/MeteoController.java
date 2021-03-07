package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.DTO.MeteoDTO;


/**
 * 
 * @author dmasone
 * @implSpec class used for map API endPoint
 *
 */
@RestController
@RequestMapping(value = "/api")
public class MeteoController {
	
	private static final String MAIN = "main";
	private static final String FEELS_LIKE = "feels_like";
	private static final String HUMIDITY= "humidity";
	private static final String TEMP_MAX = "temp_max";
	
	@Value("${api.path}")
	private String apiPath;
	@Value("${api.key}")
	private String apiKey;
	
	/**
	 * 
	 * @return Test for check webApp works on
	 */
	@RequestMapping("/")
	public String index() {
		return "IT WORKS!!";
	}
	
	/**
	 * 
	 * @param cityName
	 * @return sub-model like design specification
	 */
	@GetMapping(path = "/meteo/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MeteoDTO> getMeteoByHours(@PathVariable(required = true, name="cityName") String cityName) {
		if (cityName == null) {
			return ResponseEntity.notFound().build();
		} else {
			final String uri = this.apiPath + "?q=" + cityName + "&APPID=" + this.apiKey;
			try {			
				RestTemplate restTemplate = new RestTemplate();
				MeteoDTO res = new MeteoDTO();
				JSONObject jsonObject = new JSONObject(restTemplate.getForObject(uri, String.class));
				
				res.setFeelsLike(jsonObject.getJSONObject(MeteoController.MAIN).getString(MeteoController.FEELS_LIKE));
				res.setHumidity(jsonObject.getJSONObject(MeteoController.MAIN).getString(MeteoController.HUMIDITY));
				res.setTempMax(jsonObject.getJSONObject(MeteoController.MAIN).getString(MeteoController.TEMP_MAX));
				
				return ResponseEntity.ok(res);
			} catch(Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
	}

}