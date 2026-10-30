package com.arakan.carlicense.owner;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arakan.carlicense.image.ImageService;
import com.arakan.carlicense.townships.TownShipsModel;
import com.arakan.carlicense.townships.TownshipService;

@Controller
@RequestMapping("/owner")
public class OwnerController {
	
	private final OwnerService ownerService;
	private final TownshipService townshipService;
	private final ImageService imageService;
	
	// constructor
	public OwnerController(
			OwnerService ownerService,
			TownshipService townshipService,
			ImageService imageService) {
		
		this.ownerService = ownerService;
		this.townshipService = townshipService;
		this.imageService = imageService;
	}
	
	// ==================================
	// owner create form
	// ==================================

	@GetMapping("/ownerform")
	public String owner(Model model) {
		
		model.addAttribute("ownDto", new OwnDto());
		model.addAttribute("ownerTypes",OwnerType.values());
		model.addAttribute("genders", Gender.values());
		model.addAttribute("townships", townshipService.township());
		return "owner/owner";
	}
	
	// ================================
	// owner save form
	// ================================
	@PostMapping("/create")
	public String create(OwnDto dto,
			RedirectAttributes redirectAttributes) throws IOException {
		Owner owner = new Owner();
		
		owner.setOwnertype(dto.getOwnertype());
		owner.setFullName(dto.getFullName());
		owner.setGender(dto.getGender());
		owner.setNrc(dto.getNrc());
		owner.setPhone(dto.getPhone());
		owner.setEmail(dto.getEmail());
		owner.setFulladdress(dto.getFulladdress());
		
		// forenign key township
		TownShipsModel township = townshipService.findById(dto.getTownshipId());
		owner.setTownship(township);
		
		// NRC  Front Image Folder
		if(dto.getNrcFrontImage() != null
				&& !dto.getNrcFrontImage().isEmpty()) {
			
			String imageName = imageService.saveNrcImage(
					dto.getNrcFrontImage());
			
			owner.setNrcFrontImage(imageName);
		}
		
		// Nrc Back Image Folder
		if(dto.getNrcBackImage() != null
				
				&& !dto.getNrcBackImage().isEmpty()) {
			String imageName = imageService.saveNrcImage(
					dto.getNrcBackImage());
			
			owner.setNrcBackImage(imageName);
		}
		
		// save database
		ownerService.create(owner);
		
		redirectAttributes.addFlashAttribute(
				"successMessage","Owner အချက်အလက် သိမ်းဆည်းပြီးပါပြီး။");
		return "redirect:/owner/ownertable";
	}
	
	// =====================================
	// Owner Table
	// =====================================
	@GetMapping("/ownertable")
	public String ownerTable(Model model) {
		List<Owner> owners = ownerService.findAllOwner();
		
		model.addAttribute("owners", owners);
		return "owner/ownertable";
	}
	
	// ========================================
	// edit form
	// ========================================
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id,Model model) {
		
		Owner owner = ownerService.findById(id);
		model.addAttribute("owner", owner);
		return "owner/owneredit";
	}
}