package com.arakan.carlicense.license;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arakan.carlicense.car.CarService;

import jakarta.validation.Valid;
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
		return "license/license";
	}
	
	// =================================
	// license create form
	// ==================================
	@GetMapping("/home")
	public String license(Model model) {
		model.addAttribute("dto", new LicenseDto());
		model.addAttribute("cars", licenseSer.getCarsWithoutLicense());
		return "license/home";
	}
	
	// ================================
	// create form
	// ================================
	@PostMapping("/create")
	public String save(@Valid @ModelAttribute("dto") LicenseDto dto,
			BindingResult result,
			RedirectAttributes redirect,
			Model model) {
		if(result.hasErrors()) {
			model.addAttribute("cars", carService.carList());
			return "license/home";
		}try {
			licenseSer.create(dto);
			redirect.addFlashAttribute("success","License is successfully");
			return "redirect:/license/list";
		}catch(RuntimeException e) {
			model.addAttribute("cars", carService.carList());
			model.addAttribute("error", e.getMessage());
			return "license/home";
		}
		
		
		
	}
	
	// =================================
	// license edit form
	// =================================
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id,Model model) {
		License licenses = licenseSer.findlicenseid(id);
		
		LicenseDto dto = new LicenseDto();
		// forenigy key
		dto.setCarId(licenses.getCar().getId());
		dto.setLicensenum(licenses.getLicensenum());
		dto.setStatus(licenses.getStatus());
		dto.setIssueDate(licenses.getIssueDate());
		dto.setExpiryDate(licenses.getExpiryDate());
		dto.setRemarks(licenses.getRemarks());
		
		System.out.println("Issue Date: " + licenses.getIssueDate());
		System.out.println("Expiry Date: " + licenses.getExpiryDate());
		
		model.addAttribute("license", licenses);
		model.addAttribute("cars",carService.carList());
		model.addAttribute("licenseDto",dto);
		
		return "license/update";
	}
	
	// ======================================
	// License Update form
	// ======================================
	@PostMapping("/update/{id}")
	public String update(@PathVariable Long id,
			@Valid @ModelAttribute("dto") LicenseDto dto,
			BindingResult result,
			RedirectAttributes redirect,
			Model model) {
		if(result.hasErrors()) {
			model.addAttribute("cars",carService.carList());
			return "license/update";
		}
		
		licenseSer.update(id, dto, dto.getCarId());
		
		redirect.addFlashAttribute("updates","License update message successfully!");
		
		return "redirect:/license/list";
	}
	
	// ======================================
	// License Delete
	// ======================================
	@PostMapping("/delete/{id}")
	public String licenseDelete(@PathVariable Long id,
			RedirectAttributes redirect) {
		licenseSer.deleteLicense(id);
		
		redirect.addFlashAttribute("deletemessage", "You Delete Message Successfully!");
		return "redirect:/license/list";
	}
	
	// ======================================
	// license number check 
	// ======================================
	@GetMapping("/check-number")
	@ResponseBody
	public boolean checkLicenseNumber(@RequestParam String licensenum) {
		return licenseSer.isLicenseNumberExists(licensenum);
	}
}
