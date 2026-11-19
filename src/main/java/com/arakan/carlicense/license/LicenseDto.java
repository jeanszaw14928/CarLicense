package com.arakan.carlicense.license;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import com.arakan.carlicense.license.ValidDateRange;
@Setter
@Getter
@ValidDateRange
public class LicenseDto {

	private Long carId;
	private String licensenum;
	private LicenseStatus status=LicenseStatus.ACTIVE;
	@NotNull(message = "Issue date is required")
	private LocalDate issueDate;
	@NotNull(message = "Expiry date is required")
	private LocalDate expiryDate;
	private String remarks;
}
