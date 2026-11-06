package com.arakan.carlicense.car;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arakan.carlicense.image.ImageService;
import com.arakan.carlicense.owner.Owner;
import com.arakan.carlicense.owner.OwnerRepository;

@Service
public class CarService {

	// car repostory
	private final Repository carRepo;
	// owner repository
	private final OwnerRepository ownerRepo;
	// image service
	private final ImageService imageService;
	
	// constructor
	private CarService(Repository carRepo,
			OwnerRepository ownerRepo,
			ImageService imageService) {
		this.carRepo = carRepo;
		this.ownerRepo= ownerRepo;
		this.imageService = imageService;
	}
	
	// =================================
	// Car All List
	// =================================
	public List<Car> carList(){
		return carRepo.findAll();
	}
	
	// ================================
	// car create method
	// ================================
	public Car create(CarDto carDto) throws IOException {
		// find owner
		Owner owner = ownerRepo.findById(carDto.getOwnerId()).orElseThrow(
				()-> new RuntimeException("Owner Not Found"));
		
		// create car
		Car car = new Car();
		
		// set Owner
		car.setOwner(owner);
		
		// set car information
		car.setPlatenum(carDto.getPlatenum());
		car.setVehicle(carDto.getVehicle());
		car.setFuelType(carDto.getFuelType());
		car.setCarbrand(carDto.getCarbrand());
		car.setCarmodel(carDto.getCarmodel());
		car.setManafactureyear(carDto.getManafactureyear());
		car.setCarcolor(carDto.getCarcolor());
		car.setChassisnum(carDto.getChassisnum());
		car.setEnginenum(carDto.getEnginenum());
		
		// save car image
		String imageName = imageService.saveCarImage(carDto.getCarimage());
		car.setCarimage(imageName);
		
		// database save
		return carRepo.save(car);
	}
}
