package PortalTracker.Tracker.repository;

import PortalTracker.Tracker.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Optional<Employee> findEmployeeById(int id);

    @Query("select e from Employee e where e.email LIKE %?1%")
    <T> List<T> searchEmployeesByEmail(String email);
}
