package com.arakan.carlicense.owner;

import com.arakan.carlicense.townships.TownShipsModel;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="owner")
@Setter
@Getter
public class Owner {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	// own type
	@Enumerated(EnumType.STRING)
	private OwnerType ownertype = OwnerType.PERSONAL;
	
	private String fullName ;
	
	// Gender
	@Enumerated(EnumType.STRING)
	private Gender gender = Gender.MALE;
	
	private String nrc;
	private String phone;
	private String email;
	// township
	@ManyToOne
	@JoinColumn(name="township_id")
	private TownShipsModel township;
	private String fulladdress;
	private String nrcFrontImage;
	private String nrcBackImage;
}
