package PortalTracker.Tracker.service.impl;

import PortalTracker.Tracker.model.RecentURL;
import PortalTracker.Tracker.repository.RecentUrlRepository;
import PortalTracker.Tracker.service.RecentUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecentUrlServiceImpl implements RecentUrlService {
	private final RecentUrlRepository recentUrlRepository;

	@Override
	public RecentURL findUrlById(int id) {
		Optional<RecentURL> recentURLOptional = recentUrlRepository.findById(id);
		if (recentURLOptional.isPresent())
			return recentURLOptional.get();
		else
			throw new RuntimeException("NOT FOUND");
	}

	@Override
	public RecentURL createRecentURL(RecentURL recentURL) {
		return recentUrlRepository.save(recentURL);
	}

	@Override
	public Page<RecentURL> findURLSByEmpId(int empID, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("dateSearched").descending());
		return recentUrlRepository.findRecentURLSByEmployeeId(empID, pageable);
	}

}
