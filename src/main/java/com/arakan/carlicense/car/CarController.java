package com.arakan.carlicense.car;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arakan.carlicense.owner.OwnerService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Controller
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
	
	// carservice
	private final CarService carService;
	// Owner Service
	private final OwnerService ownerService;

	// ======================================
	// Create Form
	// ======================================
	
	@GetMapping("/home")
	public String car(Model model) {
		
		model.addAttribute("car", new CarDto());
		model.addAttribute("owners", ownerService.findAllOwner());
		model.addAttribute("fuelTypes", FuelType.values());
		
		return "car/home";
	}
	
	// ========================================
	// Car List
	// ========================================
	@GetMapping("/list")
	public String CarList(Model model) {
		
		List<Car> cars = carService.carList();
		
		model.addAttribute("cars", cars);
		return "car/cartable";
	}
	
	// =========================================
	// Save Car
	// =========================================
	
	@PostMapping("/create")
	public  String create(
			@val @ModelAttribute("car") CarDto dto,
			BindingResult result,
			Model model,
			RedirectAttributes redirect) throws IOException {
		
		if(result.hasErrors()) {
			model.addAttribute("owners", ownerService.findAllOwner());
			model.addAttribute("fuelTypes", FuelType.values());
			
			return "/car/create";
		}
		
		// car save controller
		carService.create(dto);
		
		redirect.addFlashAttribute("success", "Car create successfully");
		
		return "redirect:/car/list";
		
	}
	
	// ======================================
	// car edit form
	// ======================================
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id,Model model){
		Car car = carService.findById(id);
		
		CarDto dto = new CarDto();
		// forenigy
		dto.setOwnerId(car.getOwner().getId());
		
		dto.setPlatenum(car.getPlatenum());
		dto.setVehicle(car.getVehicle());
		dto.setFuelType(car.getFuelType());
		dto.setCarbrand(car.getCarbrand());
		dto.setCarmodel(car.getCarmodel());
		dto.setManafactureyear(car.getManafactureyear());
		dto.setCarcolor(car.getCarcolor());
		dto.setChassisnum(car.getChassisnum());
		dto.setEnginenum(car.getEnginenum());
		
		model.addAttribute("dto", dto);
		model.addAttribute("car", car);
		model.addAttribute("owners", ownerService.findAllOwner());
		
		return "car/edit";
	}
	
	// ============================================
	// car update
	// ============================================
	@PostMapping("/update/{id}")
	public String update(@PathVariable Long id,
			@ModelAttribute CarDto dto,
			RedirectAttributes redirects) {
		
		carService.update(id, dto, dto.getOwnerId());
		
		redirects.addFlashAttribute("successupdate","Car Update Successfully!");
		return "redirect:/car/list";
	}
	
	// ==========================================
	// car delete
	// ==========================================
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Long id,
			RedirectAttributes redirects) {
		
		carService.delete(id);
		
		redirects.addFlashAttribute("deleteMessage","You Delete Successfully!");
		return "redirect:/car/list";
	}
}
