package PortalTracker.Tracker.repository;

import PortalTracker.Tracker.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<ImageData,Long> {

    Optional<ImageData> findImageDataByName(String filename);

    @Query("select imgd from ImageData imgd where imgd.employee.id=:id")
    ImageData findImageDataByEmployeeId(int id);

}
