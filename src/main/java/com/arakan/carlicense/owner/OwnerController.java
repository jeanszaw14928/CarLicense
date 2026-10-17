package com.arakan.carlicense.owner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {

	@GetMapping("/ownerform")
	public String owner() {
		return "owner/owner";
}
}