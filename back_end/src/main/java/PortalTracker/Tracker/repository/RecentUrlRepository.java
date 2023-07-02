package PortalTracker.Tracker.repository;

import PortalTracker.Tracker.model.RecentURL;
import PortalTracker.Tracker.service.RecentUrlService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecentUrlRepository extends JpaRepository<RecentURL, Integer> {

    Page<RecentURL> findRecentURLSByEmployeeId(@Param("employeeId") int employeeId, Pageable pageable);
    List<RecentURL> findAllByEmployeeId(int employeeId);
}
