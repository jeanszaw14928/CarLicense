package com.arakan.carlicense.license;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arakan.carlicense.car.CarService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/license")
@RequiredArgsConstructor
public class LicenseController {

	// license service
	private final LicenseService licenseSer;
	// car service
	private final CarService carService;
	
	// =====================================
	// license list
	// =====================================
	@GetMapping("/list")
	public String licenseAll(Model model) {
		
		List<License> licenses = licenseSer.licenseList();
		
		model.addAttribute("licenses",licenses);
		return "/license/license";
	}
	
	// =================================
	// license create form
	// ==================================
	@GetMapping("/home")
	public String license(Model model) {
		model.addAttribute("dto", new LicenseDto());
		model.addAttribute("cars", carService.carList());
		return "license/home";
	}
	
	// ================================
	// create form
	// ================================
	@PostMapping("/create")
	public String save(LicenseDto dto,
			RedirectAttributes redirect) {
		licenseSer.create(dto);
		redirect.addFlashAttribute("success","License is successfully");
		return "redirect:/license/list";
	}
}
