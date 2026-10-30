package com.arakan.carlicense.townships;

import java.util.List;

import org.springframework.stereotype.Service;

@Service

public class TownshipService {

	// repository
	private final TownShipRepo repo;
	
	// constructro
	public TownshipService(TownShipRepo repo) {
		this.repo = repo;
	}
	
	// ===============
	// create
	// ===============
	public  void create(String name) {
		TownShipsModel township = new TownShipsModel();
		township.setName(name);
		repo.save(township);
	}
	
	// ===================
	// list
	// ===================
	public List<TownShipsModel> township(){
		List<TownShipsModel> townshiplist = repo.findAll();
		return townshiplist;
	}
	
	// ================
	// find by id
	// ================
	public TownShipsModel findById(Integer id) {
		return repo.findById(id).orElseThrow( () -> new RuntimeException("TownShip not found"));
	}
	
	// ==============
	// update
	// ==============
	public TownShipsModel update(Integer id, TownShipsModel newtownship) {
		TownShipsModel townships = findById(id);
		townships.setName(newtownship.getName());
		
		return repo.save(townships);
	}
	
	// ================
	// delete
	// ================
	public void delete(Integer id) {
		TownShipsModel township = repo.findById(id).orElseThrow( () -> new RuntimeException("Township not found"));
		repo.delete(township);
	}
}
