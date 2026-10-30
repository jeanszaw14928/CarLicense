package com.arakan.carlicense.owner;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arakan.carlicense.image.ImageService;
import com.arakan.carlicense.townships.TownShipRepo;
import com.arakan.carlicense.townships.TownShipsModel;

@Service
public class OwnerService {

	private final OwnerRepository repo;
	// township repo
	private final TownShipRepo townshiprepo;
	// image service
	private final ImageService imageService;
	
	private OwnerService(OwnerRepository repo,
			TownShipRepo townshiprepo,
			ImageService imageService) {
		this.repo = repo;
		this.townshiprepo = townshiprepo;
		this.imageService = imageService;
	}
	
	// ==================
	// owner list
	// ==================
	public List<Owner> findAllOwner(){
		return repo.findAll();
	}
	
	// ====================
	// owner find by id
	// =====================
	public Owner findById(Long id) {
		return repo.findById(id).orElseThrow( ()-> new RuntimeException("Owner not found"));
	}
	
	// ==================
	// owner create
	// ==================
	public void create(Owner owner) {
		
		repo.save(owner);
	}
	
	// ==================
	// update
	// ==================
	public Owner update(Long id,Integer township_id, OwnDto ownerDto) {
		// find by owner id
		Owner owner = repo.findById(id).orElseThrow( ()-> new RuntimeException("Owner update not found"));
		
		// find by township id from owner
		TownShipsModel township = townshiprepo.findById(township_id).orElseThrow( () -> new RuntimeException("township not found"));
		
		// to save owner data
		owner.setOwnertype(ownerDto.getOwnertype());
		owner.setFullName(ownerDto.getFullName());
		owner.setGender(ownerDto.getGender());
		owner.setNrc(ownerDto.getNrc());
		owner.setPhone(ownerDto.getPhone());
		owner.setEmail(ownerDto.getEmail());
		owner.setTownship(township);
		owner.setFulladdress(ownerDto.getFulladdress());
		
		//Image Service for FrontImage
		try {
			// image ကို save ပြီး file name(string) ယူခြင်း
			if(ownerDto.getNrcFrontImage() != null && !ownerDto.getNrcFrontImage().isEmpty()) {
				String frontImageName = imageService.saveNrcImage(ownerDto.getNrcFrontImage());
				owner.setNrcFrontImage(frontImageName);
			}
			// image ကို save ပြီး file name(string) ယူခြင်း
			if(ownerDto.getNrcBackImage() != null && !ownerDto.getNrcBackImage().isEmpty()) {
				String BackImageName = imageService.saveNrcImage(ownerDto.getNrcBackImage());
				owner.setNrcFrontImage(BackImageName);
			}
		}catch(IOException e) {
			throw new RuntimeException("Failed to store image file: " + e.getMessage());
		}
		

		return repo.save(owner);
	}
}
