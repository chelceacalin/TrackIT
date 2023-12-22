package PortalTracker.Tracker.service.impl;

import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.ImageData;
import PortalTracker.Tracker.repository.ImageDataRepository;
import PortalTracker.Tracker.service.EmployeeService;
import PortalTracker.Tracker.service.ImageDataService;
import PortalTracker.Tracker.util.ImageDataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageDataServiceImpl implements ImageDataService {

	final ImageDataRepository imageDataRepository;
	final ImageDataUtil util;
	final EmployeeService employeeService;

	@Override
	public String uploadImage(MultipartFile file, int empID) throws Exception {
		Optional<Employee> e1 = employeeService.findById(empID);
		ImageData data;
		if (e1.isPresent()) {
			data = imageDataRepository.save(ImageData.builder()
					.name(file.getOriginalFilename())
					.type(file.getContentType())
					.imageData(util.compressImage(file.getBytes()))
					.employee(e1.get())
					.build());
			e1.get().setImageData(data);
			employeeService.updateEmployee(e1.get().getId(), e1.get());
		} else {
			data = imageDataRepository.save(ImageData.builder()
					.name(file.getOriginalFilename())
					.type(file.getContentType())
					.imageData(util.compressImage(file.getBytes()))
					.build());
		}

		if (!data.equals("")) {
			return "File uploaded successfully " + file.getOriginalFilename();
		} else
			return null;
	}

	public byte[] downloadImage(String fileName) {
		Optional<ImageData> data = imageDataRepository.findImageDataByName(fileName);
		return data.map(imageData -> util.decompressImage(imageData.getImageData())).orElse(null);
	}

	@Override
	public byte[] findImageDataByEmployeeId(int id) {
		ImageData data = imageDataRepository.findImageDataByEmployeeId(id);
		return util.decompressImage(data.getImageData() != null ? data.getImageData() : new byte[]{});
	}
}
