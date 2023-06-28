package PortalTracker.Tracker.service;

import PortalTracker.Tracker.model.RecentURL;
import org.springframework.data.domain.Page;

public interface RecentUrlService {
    Page<RecentURL> getMostRecentUrls(int pageNo,int pageSize);
    RecentURL createRecentUrl(RecentURL recentURL);
}
