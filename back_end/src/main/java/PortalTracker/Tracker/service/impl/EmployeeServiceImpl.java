package PortalTracker.Tracker.service.impl;

import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.repository.EmployeeRepository;
import PortalTracker.Tracker.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            throw new Exception("Employee not found");
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
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            return repository.save(existingEmployee);
        }

        return new Employee();
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
                    throw new UsernameNotFoundException("User not found");
            }
        };
    }


}
