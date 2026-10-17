package com.arakan.carlicense.license;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/license")
public class LicenseController {

	@GetMapping("/home")
	public String license() {
		return "license/home";
	}
}
