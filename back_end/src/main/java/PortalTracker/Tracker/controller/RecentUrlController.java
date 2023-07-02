package PortalTracker.Tracker.controller;

import PortalTracker.Tracker.model.RecentURL;
import PortalTracker.Tracker.service.RecentUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
public class RecentUrlController {
    private final RecentUrlService service;

    @Autowired
    public RecentUrlController(RecentUrlService service) {
        this.service = service;
    }

    @PostMapping("/recentlyOpenedUrl")
    public ResponseEntity<RecentURL> createRecentUrl(@RequestBody RecentURL recentURL) {
        RecentURL createdRecentURL = service.createRecentUrl(recentURL);
        return new ResponseEntity<>(createdRecentURL, HttpStatus.CREATED);
    }

    @GetMapping("/recentlyOpenedUrl/{employeeId}")
    public ResponseEntity<List<RecentURL>> getAllRecentURLsByEmployeeId(@PathVariable int employeeId) {
        List<RecentURL> recentURLs = service.getAllRecentURLsByEmployeeId(employeeId);
        return new ResponseEntity<>(recentURLs, HttpStatus.OK);
    }
}
