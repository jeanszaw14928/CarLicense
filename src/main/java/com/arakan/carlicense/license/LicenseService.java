package com.arakan.carlicense.license;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arakan.carlicense.car.Car;
import com.arakan.carlicense.car.Repository;

import jakarta.transaction.Transactional;
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
	@Transactional
	public License create(LicenseDto dto) {
		Car car = carRepo.findById(dto.getCarId()).orElseThrow(() -> new RuntimeException("Car not found"));
		
		// Do have checked car license
		if(repo.existsByCarId(car.getId())) {
			throw new RuntimeException("This car already has a License Number");
		}
		
		// the same car license have filter
		if(repo.existsByLicensenum(dto.getLicensenum())) {
			throw new RuntimeException(
					"This License Number already exists!");
		};
		
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
	
	// ========================================
	// License find by id
	// ========================================
	public License findlicenseid(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Car License not found"));
	}
	
	// =========================================
	// License update
	// =========================================
	public License update(Long id,
			LicenseDto dto,
			Long carId) {
		
		// license find by id
		License license = findlicenseid(id);
		
		// car find by id
		Car car = carRepo.findById(carId).orElseThrow(() -> new RuntimeException("Car not found id!"));
		
		// update license
		license.setCar(car);
		license.setLicensenum(dto.getLicensenum());
		license.setStatus(dto.getStatus());
		license.setIssueDate(dto.getIssueDate());
		license.setExpiryDate(dto.getExpiryDate());
		license.setRemarks(dto.getRemarks());
		
		// update save
		return repo.save(license);
	}
	
	// =======================================
	// License Delete
	// =======================================
	public void deleteLicense(Long id) {
		License license = findlicenseid(id);
		repo.delete(license);
	}
	
	// ======================================
	// check expired license
	// ======================================
	@Transactional
	public void checkExpiredLicenses() {
		LocalDate today = LocalDate.now();
		
		int count =repo.updateExpiredLicenses(today,
				LicenseStatus.ACTIVE, 
				LicenseStatus.EXPIRED);
		System.out.println("Expired License: " + count);
	}
	
	// =====================================
	// licenseမရှိသေးရေကောင်ကိုပြချင်တာ
	// =====================================
	public List<Car> getCarsWithoutLicense(){
		return carRepo.findAll().stream().filter(car -> !repo.existsByCarId(car.getId())).toList();
	}
	
	// ======================================
	// license number ရှိမရှိ စစ်ဆေးမဲ့ method
	// ======================================
	public boolean isLicenseNumberExists(String licensenumber) {
		return repo.existsByLicensenum(licensenumber.trim());
	}
	
}
