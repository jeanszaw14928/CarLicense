package com.arakan.carlicense.townships;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	private int id;
	private String name;

}
