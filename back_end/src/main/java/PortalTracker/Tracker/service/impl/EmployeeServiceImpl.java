package PortalTracker.Tracker.service.impl;

import PortalTracker.Tracker.exception.EntityNotFoundException;
import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.repository.EmployeeRepository;
import PortalTracker.Tracker.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	final EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> findAll(Specification<Employee> specification) {
		return employeeRepository.findAll(specification);
	}

	@Override
	public Optional<Employee> findById(int id) throws Exception {
		Optional<Employee> emp = employeeRepository.findById(id);
		if (emp.isPresent()) {
			return emp;
		} else
			throw new EntityNotFoundException("Employee with id " + id + " not found");
	}

	@Override
	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(int id, Employee employee) {
		Optional<Employee> optionalEmployee = employeeRepository.findEmployeeById(id);
		if (optionalEmployee.isPresent()) {
			return employeeRepository.save(employee);
		}
		return employee;
	}

	@Override
	public Page<Employee> findAllByPage(int pageNo, int pageSize) {
		Pageable page = PageRequest.of(pageNo, pageSize);
		return employeeRepository.findAll(page);
	}

	@Override
	public <T> List<T> searchEmployeesByEmail(String email) {
		return employeeRepository.searchEmployeesByEmail(email);
	}

	@Override
	public Employee getEmpByEmail(String email) {
		Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(email);
		if (employeeOptional.isPresent()) {
			return employeeOptional.get();
		} else {
			throw new RuntimeException("Employee not found");
		}
	}

	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Optional<Employee> emp = employeeRepository.findEmployeeByEmail(username);
				if (emp.isPresent())
					return emp.get();
				else
					throw new EntityNotFoundException("User with username " + username + " found");
			}
		};
	}

	@Override
	public List<Employee> findAllEmployeesDynamicFilter(String firstName, String lastName, String email, String password) {
		return employeeRepository.findAllEmployeesDynamicFilter(firstName, lastName, email, password);
	}


}
