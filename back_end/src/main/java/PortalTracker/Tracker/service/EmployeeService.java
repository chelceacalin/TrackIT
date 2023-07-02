package PortalTracker.Tracker.service;

import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.service.impl.EmployeeServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();
    Optional<Employee> findById(int id) throws Exception;

    void deleteEmployee(int id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(int id,Employee employee);

    Page<Employee> findAllByPage(int pageNo,int pageSize);

    <T> List<T> searchEmployeesByEmail(String email);

    Employee getEmpByEmail(String email);
    UserDetailsService userDetailsService();
}
