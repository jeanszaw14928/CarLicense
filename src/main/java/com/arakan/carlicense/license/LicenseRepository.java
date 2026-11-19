package com.arakan.carlicense.license;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LicenseRepository extends JpaRepository<License,Long>{
	@Modifying
	@Query("""
			UPDATE License l
			SET l.status = :expired
			WHERE l.status = :active
			AND l.expiryDate < :today
			""")
	int updateExpiredLicenses(
			@Param("today") LocalDate today,
			@Param("active") LicenseStatus active,
			@Param("expired") LicenseStatus expired
			);
	
	// ဒီကားမှာ license ရှိပြီးသားလား စစ်မယ်
	boolean existsByCarId(Long carId);
	// car latest license search
	Optional<License> findTopByCarIdOrderByIdDesc(Long id);
	
	// car license number filter
	boolean existsByLicensenum(String licensenum);
}
