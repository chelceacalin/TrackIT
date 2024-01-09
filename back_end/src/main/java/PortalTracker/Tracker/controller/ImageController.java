package PortalTracker.Tracker.controller;

import PortalTracker.Tracker.service.ImageDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ImageController {

	final ImageDataService imageDataService;

	@PostMapping("/images/{employeeId}")
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable int employeeId) throws Exception {
		String upload = imageDataService.uploadImage(file, employeeId);
		return ResponseEntity.ok(upload);
	}

	@GetMapping("/images/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
		byte[] imgData = imageDataService.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imgData);

	}

	@GetMapping("/imagesByEmpId/{id}")
	public ResponseEntity<?> getImageDataByEmpID(@PathVariable(name = "id") int employeeID) {
		byte[] imgData = imageDataService.findImageDataByEmployeeId(employeeID);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imgData);
	}
}
