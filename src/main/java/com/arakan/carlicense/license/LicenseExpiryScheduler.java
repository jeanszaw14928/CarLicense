package com.arakan.carlicense.license;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class LicenseExpiryScheduler {
	private final LicenseService licenseService;
	
	@Scheduled(cron = "0 0 0 * * *", zone = "Asia/Yangon")
	public void checkExpiryDate() {
		licenseService.checkExpiredLicenses();
		log.info("License expiry check completed.");
	}
}
