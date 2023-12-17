package PortalTracker.Tracker.controller;

import PortalTracker.Tracker.dto.EmployeeDTO;
import PortalTracker.Tracker.exception.EntityNotFoundException;
import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.service.EmployeeService;
import PortalTracker.Tracker.specification.EmployeeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class EmployeeController {

	final EmployeeService employeeService;

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}

	@DeleteMapping("/employees/{id}")
	public Employee deleteEmployee(@PathVariable(name = "id") int id) throws Exception {
		Optional<Employee> e1 = employeeService.findById(id);
		if (e1.isPresent()) {
			employeeService.deleteEmployee(id);
			return e1.get();
		} else
			throw new Exception("Employee not existent");

	}

	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable("id") int employeeId, @RequestBody Employee employee) {
		return employeeService.updateEmployee(employeeId, employee);
	}

	@GetMapping("employees/{id}")
	public Employee getEmployeeById(@PathVariable int id) throws Exception {
		Optional<Employee> eOptional = employeeService.findById(id);
		if (eOptional.isPresent()) return eOptional.get();
		else throw new EntityNotFoundException("Employee with id " + id + " not found");
	}


	@GetMapping("/employees")
	public List<Employee> getEmpPages(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
		return employeeService.findAllByPage(pageNo, pageSize).getContent();
	}

	@GetMapping("/employeesDTO")
	public List<EmployeeDTO> getEmpPagesDTO(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
		return employeeService.findAllByPage(pageNo, pageSize).getContent().stream().map((emp) -> {
			EmployeeDTO employeeDto = new EmployeeDTO();
			BeanUtils.copyProperties(emp, employeeDto);
			return employeeDto;
		}).toList();
	}

	@GetMapping("/employeesByMail")
	public List<Employee> searchEmployeeByEmail(@RequestParam("email") String email) {
		return employeeService.searchEmployeesByEmail(email);
	}

	@GetMapping("/employeeByEmail")
	public Employee findByMailEmp(@RequestParam("email") String email) {
		return employeeService.getEmpByEmail(email);
	}


	@GetMapping("/allEmps")
	public ResponseEntity<List<Employee>> getAllEmpsDynamicFilter(
			@RequestParam(name = "firstName", required = false) String first,
			@RequestParam(name = "lastName", required = false) String last,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "password", required = false) String pass
	) {
		if (first == null && last == null && email == null && pass == null) {

			List<Employee> list = employeeService.findAll();
			return ResponseEntity.ok(list);
		}

		List<Employee> allEmployeesDynamicFilter = employeeService.findAllEmployeesDynamicFilter(first, last, email, pass);
		return new ResponseEntity<>(allEmployeesDynamicFilter, HttpStatus.OK);
	}


	@GetMapping("/empsFilter")
	public ResponseEntity<List<Employee>> filterEmployees(@RequestParam(required = false) String firstName,
														  @RequestParam(required = false) List<String> firstNameIn,
														  @RequestParam(required = false) Integer lastNameLengthGreaterThan) {

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

		List<Employee> filteredEmployees = employeeService.findAll(specifications);
		return ResponseEntity.ok(filteredEmployees);
	}


}
