package PortalTracker.Tracker.service.impl;

import PortalTracker.Tracker.model.RecentURL;
import PortalTracker.Tracker.repository.RecentUrlRepository;
import PortalTracker.Tracker.service.RecentUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecentUrlServiceImpl implements RecentUrlService {
    private final RecentUrlRepository repository;

    @Autowired
    public RecentUrlServiceImpl(RecentUrlRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<RecentURL> findRecentURLsByEmployeeId(Integer employeeId, Pageable pageable) {
        return repository.findRecentURLSByEmployeeId(employeeId,pageable);
    }

    @Override
    public RecentURL createRecentUrl(RecentURL recentURL) {
        return repository.save(recentURL);
    }

    @Override
    public List<RecentURL> getAllRecentURLsByEmployeeId(int employeeId) {
        return repository.findAllByEmployeeId(employeeId);
    }
}
