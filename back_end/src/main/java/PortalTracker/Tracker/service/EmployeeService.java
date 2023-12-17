package PortalTracker.Tracker.service;

import PortalTracker.Tracker.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
	List<Employee> findAll();

    List<Employee> findAll(Specification<Employee> specification);

	Optional<Employee> findById(int id) throws Exception;

	void deleteEmployee(int id);

	Employee createEmployee(Employee employee);

	Employee updateEmployee(int id, Employee employee);

	Page<Employee> findAllByPage(int pageNo, int pageSize);

	<T> List<T> searchEmployeesByEmail(String email);

	Employee getEmpByEmail(String email);

	UserDetailsService userDetailsService();


	List<Employee> findAllEmployeesDynamicFilter(String firstName, String lastName, String email, String password);
}
