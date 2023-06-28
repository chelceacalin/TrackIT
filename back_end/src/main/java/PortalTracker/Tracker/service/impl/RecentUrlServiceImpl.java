package PortalTracker.Tracker.service.impl;

import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.RecentURL;
import PortalTracker.Tracker.repository.RecentUrlRepository;
import PortalTracker.Tracker.service.RecentUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RecentUrlServiceImpl implements RecentUrlService {
    RecentUrlRepository repository;

    @Autowired
    public RecentUrlServiceImpl(RecentUrlRepository repository){
        this.repository=repository;
    }

    @Override
    public Page<RecentURL> getMostRecentUrls(int pageNo, int pageSize) {
        Pageable page=PageRequest.of(pageNo,pageSize,Sort.by("dateSearched"));
        return repository.findAll(page);
    }

    public RecentURL createRecentUrl(RecentURL recentURL){
        return repository.save(recentURL);
    }
}
