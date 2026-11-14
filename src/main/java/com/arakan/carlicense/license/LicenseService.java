package com.arakan.carlicense.license;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arakan.carlicense.car.Car;
import com.arakan.carlicense.car.Repository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LicenseService {

	// license repository
	private final LicenseRepository repo;
	// Car Repository
	private final Repository carRepo;
	
	// consturctor
	//public LicenseService(LicenseRepository repo) {
		//this.repo = repo;
	//}
	
	// ========================================
	// License List
	// ========================================
	public List<License> licenseList(){
		return repo.findAll();
	}
	
	// ========================================
	// License create
	// ========================================
	public License create(LicenseDto dto) {
		Car car = carRepo.findById(dto.getCarId()).orElseThrow(() -> new RuntimeException("Car not found"));
		
		// create license
		License license = new License();
		license.setCar(car);
		license.setLicensenum(dto.getLicensenum());
		license.setStatus(dto.getStatus());
		license.setIssueDate(dto.getIssueDate());
		license.setExpiryDate(dto.getExpiryDate());
		license.setRemarks(dto.getRemarks());
		
		// save
		return repo.save(license);
	}
	
}
