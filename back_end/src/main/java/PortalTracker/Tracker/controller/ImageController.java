package PortalTracker.Tracker.controller;

import PortalTracker.Tracker.service.ImageDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ImageController {

	private final ImageDataService service;

	@PostMapping("/images/{employeeId}")
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable int employeeId) throws Exception {
		String upload = service.uploadImage(file, employeeId);
		return ResponseEntity.ok(upload);
	}

	@GetMapping("/images/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws IOException {
		byte[] imgData = service.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imgData);

	}

	@GetMapping("/imagesByEmpId/{id}")
	public ResponseEntity<?> getImageDataByEmpID(@PathVariable(name = "id") int employeeID) {
		byte[] imgData = service.findImageDataByEmployeeId(employeeID);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imgData);
	}
}
