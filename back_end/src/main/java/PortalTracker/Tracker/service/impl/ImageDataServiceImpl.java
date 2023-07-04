package PortalTracker.Tracker.service.impl;

import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.ImageData;
import PortalTracker.Tracker.repository.ImageDataRepository;
import PortalTracker.Tracker.service.EmployeeService;
import PortalTracker.Tracker.service.ImageDataService;
import PortalTracker.Tracker.util.ImageDataUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataServiceImpl implements ImageDataService {

    private ImageDataRepository repository;
    private ImageDataUtil util;
    private EmployeeService employeeService;


    public ImageDataServiceImpl(ImageDataRepository repository,ImageDataUtil util,EmployeeService employeeService) {
        this.repository = repository;
        this.util=util;
        this.employeeService=employeeService;
    }


    @Override
    public String uploadImage(MultipartFile file,int empID) throws Exception {
        Optional<Employee> e1=employeeService.findById(empID);
        ImageData data;
        if(e1.isPresent()){
            data= repository.save(ImageData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .imageData(util.compressImage(file.getBytes()))
                    .employee(e1.get())
                    .build());
            System.out.println(e1.toString());
            e1.get().setImageData(data);
            employeeService.updateEmployee(e1.get().getId(),e1.get());
        }
      else
        {
             data= repository.save(ImageData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .imageData(util.compressImage(file.getBytes()))
                    .build());
        }

        if(!data.equals("")){
            return "File uploaded successfully "+file.getOriginalFilename();
        }
        else
            return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> data=repository.findImageDataByName(fileName);
        if(data.isPresent()){
            byte[]image= util.decompressImage(data.get().getImageData());
            return image;
        }
        return null;
    }

    @Override
    public byte[] findImageDataByEmployeeId(int id) {
        ImageData data=repository.findImageDataByEmployeeId(id);
        byte[] image=util.decompressImage(data.getImageData());
        return image;
    }
}
