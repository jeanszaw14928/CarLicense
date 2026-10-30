package com.arakan.carlicense.townships;

import java.util.ArrayList;
import java.util.List;

import com.arakan.carlicense.owner.Owner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="townships")
@Setter
@Getter
public class TownShipsModel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Integer id;
	private String name;

	// for owner
	@OneToMany(mappedBy = "township")
	private List<Owner> owners = new ArrayList<>();
}
