package com.arakan.carlicense.image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class ImageService {

	@Value("${app.upload.root-dir}")
	private String uploadRootDir;
	
	public Path getUploadPath(String folderName) throws IOException {
		Path root = Paths.get(uploadRootDir)
				.toAbsolutePath().normalize();
		
		Path folder = root.resolve(folderName)
				.normalize();
		
		if(!folder.startsWith(root)) {
			throw new IllegalArgumentException("Invalid folder name");
		}
		
		Files.createDirectories(folder);
		
		return folder;
	}
	
	// controller က ခေါ်သုံးနေတဲ့ NRC image သိမ်းမည့် method
	public String saveNrcImage(MultipartFile file) throws IOException {
		if(file == null || file.isEmpty()) {
			return null;
		}
		
		// save image folder name
		Path uploadPath = getUploadPath("nrc");
		
		// ဖိုင်နာမည် တူတာတွေ မရောထွေးသွားအောင် ရှေ့က timestamp ထည့်ခြင်း
		String originalFilename = file.getOriginalFilename();
		String fileName = System.currentTimeMillis() + "_" + originalFilename;
		
		Path filePath = uploadPath.resolve(fileName);
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	
		// databaseထဲ သိမ်းဖို့ ဖိုင်နာမေကို ပြန်ပေးသည်
		return fileName;
	}
}
