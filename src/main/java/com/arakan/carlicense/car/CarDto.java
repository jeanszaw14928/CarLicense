package com.arakan.carlicense.car;

import org.springframework.web.multipart.MultipartFile;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class CarDto {

	private Long id;
	private Long ownerId;
	private String platenum;
	private String vehicle;
	private FuelType fuelType = FuelType.CHOOSEFULE;
	private String carbrand;
	private String carmodel;
	private Integer manafactureyear;
	private String carcolor;
	private String chassisnum;
	private String enginenum;
	private MultipartFile carimage;
}
