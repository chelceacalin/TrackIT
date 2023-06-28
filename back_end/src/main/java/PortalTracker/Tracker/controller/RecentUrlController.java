package PortalTracker.Tracker.controller;

import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.RecentURL;
import PortalTracker.Tracker.service.RecentUrlService;
import PortalTracker.Tracker.service.impl.RecentUrlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class RecentUrlController {

    RecentUrlService service;
    @Autowired
    public RecentUrlController(RecentUrlService service){
        this.service=service;
    }

    @GetMapping("/recentlyOpenedUrl")
    public List<RecentURL> listUrlSorted(@RequestParam(name = "pageNo",defaultValue = "0") int pageNo,
                                         @RequestParam(name = "pageSize",defaultValue = "3") int pageSize){

         return service.getMostRecentUrls(pageNo,pageSize).getContent();
    }

    @PostMapping("/recentlyOpenedUrl")
    public RecentURL createRecentUrl(@RequestBody RecentURL recentURL){
        return service.createRecentUrl(recentURL);
    }
}
