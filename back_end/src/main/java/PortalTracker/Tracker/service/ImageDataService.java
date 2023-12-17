package PortalTracker.Tracker.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageDataService {

	String uploadImage(MultipartFile file, int empID) throws Exception;

	byte[] downloadImage(String fileName);

	byte[] findImageDataByEmployeeId(int id);
}
