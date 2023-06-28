package PortalTracker.Tracker.repository;

import PortalTracker.Tracker.model.RecentURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentUrlRepository extends JpaRepository<RecentURL,Integer> {

}
