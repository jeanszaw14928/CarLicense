package com.arakan.carlicense.car;

import java.util.ArrayList;
import java.util.List;

import com.arakan.carlicense.license.License;
import com.arakan.carlicense.owner.Owner;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "car")
@Setter
@Getter
public class Car {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	// Owner
	@ManyToOne
	@JoinColumn(name="owner_id")
	private Owner owner;
	
	private String platenum;
	private String vehicle;
	
	// Fucle type
	@Enumerated(EnumType.STRING)
	private FuelType fuelType = FuelType.CHOOSEFULE;
	
	private String carbrand;
	private String carmodel;
	private Integer manafactureyear;
	private String carcolor;
	private String chassisnum;
	private String enginenum;
	private String carimage;
	
	// for license
	@OneToMany(mappedBy = "car")
	private List<License> license = new ArrayList<>();
	
}
