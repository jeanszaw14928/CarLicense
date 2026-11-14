package com.arakan.carlicense.license;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LicenseDto {

	private Long carId;
	private String licensenum;
	private LicenseStatus status=LicenseStatus.ACTIVE;
	private LocalDate issueDate;
	private LocalDate expiryDate;
	private String remarks;
}
