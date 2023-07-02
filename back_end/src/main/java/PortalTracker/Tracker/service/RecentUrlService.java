package PortalTracker.Tracker.service;

import PortalTracker.Tracker.model.RecentURL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecentUrlService {
    Page<RecentURL> findRecentURLsByEmployeeId(Integer employeeId, Pageable pageable);

    RecentURL createRecentUrl(RecentURL recentURL);
    List<RecentURL> getAllRecentURLsByEmployeeId(int employeeId);
}
