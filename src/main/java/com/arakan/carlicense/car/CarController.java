package com.arakan.carlicense.car;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/car")
public class CarController {

	@GetMapping("/home")
	public String car() {
		return "car/home";
	}
}
