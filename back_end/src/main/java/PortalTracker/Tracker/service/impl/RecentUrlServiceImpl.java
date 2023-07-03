package PortalTracker.Tracker.service.impl;

import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.RecentURL;
import PortalTracker.Tracker.model.Role;
import PortalTracker.Tracker.repository.RecentUrlRepository;
import PortalTracker.Tracker.service.RecentUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecentUrlServiceImpl implements RecentUrlService {
    private final RecentUrlRepository repository;

    @Autowired
    public RecentUrlServiceImpl(RecentUrlRepository repository) {
        this.repository = repository;
    }


    @Override
    public RecentURL findUrlById(int id) {
        Optional<RecentURL> opt= repository.findById(id);
        if(opt.isPresent())
        return opt.get();
        else
            throw new RuntimeException("NOT FOUND");
    }

    @Override
    public RecentURL createRecentURL(RecentURL recentURL) {
        return repository.save(recentURL);
    }

    @Override
    public Page<RecentURL> findURLSByEmpId(int empID, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("dateSearched").descending());
        return repository.findRecentURLSByEmployeeId(empID, pageable);
    }

}
