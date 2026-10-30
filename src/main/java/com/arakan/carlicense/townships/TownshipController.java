package com.arakan.carlicense.townships;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/township")
public class TownshipController {
	// township service
	private final TownshipService services;

	// constructor
	public TownshipController(TownshipService services) {
		this.services = services;
	}
	
	// ==============
	// create show form
	// ==============
	
	@GetMapping("/create")
	public String create() {
		return "township/create";
	}
	
	// =============
	// create form
	// =============
	@PostMapping("/save")
	public String save(@RequestParam String name) {
		services.create(name);
		
		return "redirect:/township/list";
	}
	
	// ================
	// township list
	// ================
	@GetMapping("/list")
	public String townshipList(Model model) {
		
		List<TownShipsModel> townships = services.township();
		
		model.addAttribute("townships", townships);
		return "township/townshipindex";
	}
	
	// =============
	// edit form
	// =============
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,Model model) {
		TownShipsModel township = services.findById(id);
		
		model.addAttribute("township", township);
		return "township/edit";
	}
	
	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id,
			@ModelAttribute TownShipsModel township) {
	
		services.update(id, township);
		
		return "redirect:/township/list";
	}
	
	// ===============
	// delete
	// ===============
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		services.delete(id);
		
		return "redirect:/township/list";
	}
	
}
