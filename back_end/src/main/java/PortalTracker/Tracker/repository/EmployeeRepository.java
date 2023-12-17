package PortalTracker.Tracker.repository;

import PortalTracker.Tracker.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> , JpaSpecificationExecutor<Employee> {

    Optional<Employee> findEmployeeById(int id);

    Optional<Employee> findEmployeeByEmail(String email);

    @Query("select e from Employee e where e.email LIKE %?1% or e.firstName like %?1%")
    <T> List<T> searchEmployeesByEmail(String email);


    @Query("select e from Employee e where " +
            "(e.firstName=:firstName or :firstName is NULL ) " +
            "and " +
            "(e.lastName=:lastName or :lastName is null )" +
            " and" +
            "( e.email=:email or :email is NULL )" +
            "and( e.password=:password or :password is null )")
    List<Employee> findAllEmployeesDynamicFilter(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            @Param("password") String password
            );
}
