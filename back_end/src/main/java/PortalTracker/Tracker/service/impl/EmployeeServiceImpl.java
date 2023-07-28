package PortalTracker.Tracker.service.impl;

import PortalTracker.Tracker.exception.EntityNotFoundException;
import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.ImageData;
import PortalTracker.Tracker.repository.EmployeeRepository;
import PortalTracker.Tracker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Employee> findById(int id) throws Exception {
        Optional<Employee> emp = repository.findById(id);
        if (emp.isPresent()) {
            return emp;
        } else
            throw new EntityNotFoundException("Employee with id "+id+" not found");
    }

    @Override
    public void deleteEmployee(int id) {
        repository.deleteById(id);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        Optional<Employee> optionalEmployee = repository.findEmployeeById(id);
        if (optionalEmployee.isPresent()) {
            return repository.save(employee);
        }
        return employee;
    }

    @Override
    public Page<Employee> findAllByPage(int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        return repository.findAll(page);
    }

    @Override
    public <T> List<T> searchEmployeesByEmail(String email) {
        return repository.searchEmployeesByEmail(email);
    }

    @Override
    public Employee getEmpByEmail(String email) {
        return repository.findEmployeeByEmail(email).get();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<Employee> emp = repository.findEmployeeByEmail(username);
                if (emp.isPresent())
                    return emp.get();
                else
                    throw new EntityNotFoundException("User with username "+username+" found");
            }
        };
    }

    @Override
    public List<Employee> findAllEmployeesDynamicFilter(String firstName, String lastName, String email, String password) {
        return repository.findAllEmployeesDynamicFilter(firstName,lastName,email,password);
    }


}
