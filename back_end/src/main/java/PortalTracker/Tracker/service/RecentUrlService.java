package PortalTracker.Tracker.service;

import PortalTracker.Tracker.model.RecentURL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecentUrlService {

    RecentURL findUrlById(int id);
    RecentURL createRecentURL(RecentURL recentURL);

     Page<RecentURL> findURLSByEmpId(int empID, int pageNo, int pageSize);
}
