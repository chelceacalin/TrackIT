package PortalTracker.Tracker.service;

import PortalTracker.Tracker.model.RecentURL;
import org.springframework.data.domain.Page;

public interface RecentUrlService {

	RecentURL findUrlById(int id);

	RecentURL createRecentURL(RecentURL recentURL);

	Page<RecentURL> findURLSByEmpId(int empID, int pageNo, int pageSize);
}
