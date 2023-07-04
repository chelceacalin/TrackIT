package PortalTracker.Tracker.service;

import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.ImageData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageDataService {

    String uploadImage(MultipartFile file, int empID) throws Exception;
    byte[] downloadImage(String fileName);
    byte[] findImageDataByEmployeeId(int id);
}
