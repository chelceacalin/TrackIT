package PortalTracker.Tracker.controller;

import PortalTracker.Tracker.dao.response.RecentURLDto;
import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.RecentURL;
import PortalTracker.Tracker.service.RecentUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
public class RecentUrlController {
    private final RecentUrlService service;

    @Autowired
    public RecentUrlController(RecentUrlService service) {
        this.service = service;
    }


    @GetMapping("/recentlyOpenedURL/{recentURLID}")
    public RecentURL findById(@PathVariable(name = "recentURLID") int id){
        return service.findUrlById(id);
    }


    @PostMapping("/recentlyOpenedURL")
    public RecentURL createRecentURL(@RequestBody RecentURL recentURL){
        return service.createRecentURL(recentURL);
    }

    @GetMapping("/recentlyOpenedURL/{employeeID}/recentURLs")
    public Page<RecentURLDto> getRecentURLsByEmployeeId(
            @PathVariable(name = "employeeID") int employeeID,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "6") int pageSize) {

        Page<RecentURL> recentURLs = service.findURLSByEmpId(employeeID, pageNo, pageSize);
        Page<RecentURLDto> recentURLDtos = recentURLs.map(recentURL ->
                new RecentURLDto(recentURL.getPath(), recentURL.getDateSearched()));

        return recentURLDtos;
    }



}
