package PortalTracker.Tracker.controller;

import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class EmployeeController {

    EmployeeService service;
    @Autowired
    public EmployeeController(EmployeeService service){
        this.service=service;
    }



//
//    @GetMapping("/employees")
//    public List<Employee> findAll(){
//        return service.findAll();
//    }


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
        else throw new Exception("Employee not found");
    }


    @GetMapping("/employees")
    public List<Employee> getEmpPages(@RequestParam(value = "pageNo",defaultValue = "1",required = false) int pageNo
            ,@RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize){
        return service.findAllByPage(pageNo,pageSize).getContent();
    }


    @GetMapping("/employeesByMail")
    public List<Employee> searchEmployeeByEmail(@RequestParam("email") String email){
        return service.searchEmployeesByEmail(email);
    }
}
