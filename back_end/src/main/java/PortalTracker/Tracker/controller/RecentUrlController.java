package PortalTracker.Tracker.controller;

import PortalTracker.Tracker.dao.response.RecentURLDto;
import PortalTracker.Tracker.model.RecentURL;
import PortalTracker.Tracker.service.RecentUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RecentUrlController {

    final RecentUrlService recentUrlService;

    @GetMapping("/recentlyOpenedURL/{recentURLID}")
    public RecentURL findById(@PathVariable(name = "recentURLID") int id){
        return recentUrlService.findUrlById(id);
    }


    @PostMapping("/recentlyOpenedURL")
    public RecentURL createRecentURL(@RequestBody RecentURL recentURL){
        return recentUrlService.createRecentURL(recentURL);
    }

    @GetMapping("/recentlyOpenedURL/{employeeID}/recentURLs")
    public Page<RecentURLDto> getRecentURLsByEmployeeId(
            @PathVariable(name = "employeeID") int employeeID,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "6") int pageSize) {

        Page<RecentURL> recentURLs = recentUrlService.findURLSByEmpId(employeeID, pageNo, pageSize);

        return recentURLs.map(recentURL ->
                new RecentURLDto(recentURL.getPath(), recentURL.getDateSearched()));
    }
}
