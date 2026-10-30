package com.arakan.carlicense.owner;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnDto {

	private OwnerType ownertype = OwnerType.PERSONAL;
	private String fullName;
	private Gender gender = Gender.MALE;
	private String nrc;
	private String phone;
	private String email;
	private Integer townshipId;
	private String fulladdress;
	private MultipartFile nrcFrontImage;
	private MultipartFile nrcBackImage;
}
