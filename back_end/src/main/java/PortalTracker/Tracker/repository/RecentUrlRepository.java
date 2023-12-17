package PortalTracker.Tracker.repository;

import PortalTracker.Tracker.model.RecentURL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentUrlRepository extends JpaRepository<RecentURL, Integer> {
        @Query("select e from RecentURL e where e.employee.id=:id")
        Page<RecentURL> findRecentURLSByEmployeeId(int id, Pageable pageable);
}
