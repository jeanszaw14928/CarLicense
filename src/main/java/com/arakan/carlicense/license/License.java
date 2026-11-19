package com.arakan.carlicense.license;

import java.time.LocalDate;


import com.arakan.carlicense.car.Car;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="license")
@Setter
@Getter
public class License {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	// forenigy key
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	
	@Column(nullable = false)
	private String licensenum;
	
	@Enumerated(EnumType.STRING)
	private LicenseStatus status = LicenseStatus.ACTIVE;	
	private LocalDate issueDate;
	private LocalDate expiryDate;
	private String remarks;
}
