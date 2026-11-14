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
	
	// ======================================
	// find by car id
	// ======================================
	public Car findById(Long id) {
		return carRepo.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
	}
	
	// ======================================
	// car update method
	// ======================================
	public Car update(
			Long id,
			CarDto carDto,
			Long ownerId) {
		
		// owner find by id
		Car car = carRepo.findById(id).orElseThrow(()-> new RuntimeException("car id not found"));
		
		// owner find by id
		Owner owner = ownerRepo.findById(ownerId).orElseThrow(() -> new RuntimeException("Owner Update not found"));
		
		// old image filename တွေကို အရင်သိမ်းမယ်
		String oldImage = car.getCarimage();
		
		// to save car data
		car.setOwner(owner);
		car.setPlatenum(carDto.getPlatenum());
		car.setVehicle(carDto.getVehicle());
		car.setFuelType(carDto.getFuelType());
		car.setCarbrand(carDto.getCarbrand());
		car.setCarmodel(carDto.getCarmodel());
		car.setManafactureyear(carDto.getManafactureyear());
		car.setCarcolor(carDto.getCarcolor());
		car.setChassisnum(carDto.getChassisnum());
		car.setEnginenum(carDto.getEnginenum());
		
		// Image Service for Car Image
			try {// image ကို saveပြီး filename ယူခြင်း
				if(carDto.getCarimage() != null && !carDto.getCarimage().isEmpty()) {
				String carImage = imageService.saveCarImage(carDto.getCarimage());
				car.setCarimage(carImage);
				}
			
			
		Car updateCar = carRepo.save(car);
		
		// Database update အောင်မြင်ကေ ပုံ‌ဟောင်းကိုဖျက်မယ်
		if(carDto.getCarimage() !=null && !carDto.getCarimage().isEmpty()) {
			imageService.deleteCarImage(oldImage);
		}
		return updateCar;
			}catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Faild to stroe image file");
		}
	}
	
	// =================================
	// car delete
	// =================================
	public void delete(Long id) {
		Car car = carRepo.findById(id).orElseThrow(() -> new RuntimeException("Car id not found!"));
		
		try {
			imageService.deleteCarImage(car.getCarimage());
			carRepo.delete(car);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Failed to delete car image :" + e.getMessage(), e);
		}
	}
}
