package PortalTracker.Tracker.controller;

import PortalTracker.Tracker.exception.EntityNotFoundException;
import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.ImageData;
import PortalTracker.Tracker.repository.EmployeeRepository;
import PortalTracker.Tracker.service.EmployeeService;
import PortalTracker.Tracker.specification.EmployeeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    EmployeeService service;
    @Autowired
    EmployeeRepository repository;
    @Autowired
    public EmployeeController(EmployeeService service){
        this.service=service;
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return service.createEmployee(employee);
    }

    @DeleteMapping("/employees/{id}")
    public Employee deleteEmployee(@PathVariable(name = "id") int id) throws Exception {
        Optional<Employee> e1=service.findById(id);
        if(e1.isPresent()){
            service.deleteEmployee(id);
        return e1.get();
        }
        else
            throw new Exception("Employee not existent");

    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable("id") int employeeId, @RequestBody Employee employee) {
        return service.updateEmployee(employeeId,employee);
    }

    @GetMapping("employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) throws Exception {
        Optional<Employee> eOptional=service.findById(id);
        if(eOptional.isPresent()) return eOptional.get();
        else throw new EntityNotFoundException("Employee with id "+id+" not found");
    }


    @GetMapping("/employees")
    public List<Employee> getEmpPages(@RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo
            ,@RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize){
        return service.findAllByPage(pageNo,pageSize).getContent();
    }


    @GetMapping("/employeesByMail")
    public List<Employee> searchEmployeeByEmail(@RequestParam("email") String email){
        return service.searchEmployeesByEmail(email);
    }

    @GetMapping("/employeeByEmail")
    public Employee findByMailEmp(@RequestParam("email") String email) {
        return service.getEmpByEmail(email);
    }


    @GetMapping("/allEmps")
    public ResponseEntity<List<Employee>> getAllEmpsDynamicFilter(
            @RequestParam(name = "firstName",required = false) String first,
            @RequestParam(name = "lastName",required = false) String last,
            @RequestParam(name = "email",required = false) String email,
            @RequestParam(name = "password",required = false) String pass
            ){
        if(first==null&&last==null&&email==null&&pass==null) {

            List<Employee> list=service.findAll();
            return ResponseEntity.ok(list);
        }

        List<Employee> allEmployeesDynamicFilter = service.findAllEmployeesDynamicFilter(first, last, email, pass);
        return new ResponseEntity<>(allEmployeesDynamicFilter, HttpStatus.OK);
    }



    // Dyanmic Filterning Specification
    @GetMapping("/empsFilter")
    public ResponseEntity<List<Employee>> filterEmployees(@RequestParam(required = false) String firstName,
                                                          @RequestParam(required = false) List<String> firstNameIn,
                                                          @RequestParam(required = false) Integer lastNameLengthGreaterThan) {

        // Build the specifications based on the provided query parameters
        Specification<Employee> specifications = Specification.where(null);

        if (firstName != null) {
            specifications = specifications.and(EmployeeSpecification.firstNameLike(firstName));
        }

        if (firstNameIn != null && !firstNameIn.isEmpty()) {
            specifications = specifications.and(EmployeeSpecification.firstNameIn(firstNameIn));
        }

        if (lastNameLengthGreaterThan != null) {
            specifications = specifications.and(EmployeeSpecification.lastNameLengthGreaterThan(lastNameLengthGreaterThan));
        }

        // Fetch the filtered employees based on the specifications
        List<Employee> filteredEmployees = repository.findAll(specifications);

        return ResponseEntity.ok(filteredEmployees);
    }


}
