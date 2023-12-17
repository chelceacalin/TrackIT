package PortalTracker.Tracker.controller;

import PortalTracker.Tracker.dao.response.RecentURLDto;
import PortalTracker.Tracker.model.RecentURL;
import PortalTracker.Tracker.service.RecentUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RecentUrlController {
    private final RecentUrlService service;

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

        return recentURLs.map(recentURL ->
                new RecentURLDto(recentURL.getPath(), recentURL.getDateSearched()));
    }
}
