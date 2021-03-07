package com.example.demo.DTO;

import lombok.Data;

/**
 * 
 * @author dmasone
 * @implSpec class used for map response
 *
 */
@Data
public class MeteoDTO {
	
  /**
   * tempMax of the weather
   * 
   * @param tempMax value
   * @return The current tempMax value
   */
	private String tempMax;
	
  /**
   * humidity of the weather
   * 
   * @param humidity value
   * @return The current humidity value
   */
	private String humidity;
	
  /**
   * feelsLike of the weather
   * 
   * @param feelsLike value
   * @return The current feelsLike value
   */
	private String feelsLike;
}
