package PortalTracker.Tracker.api.repository;

import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.Role;
import PortalTracker.Tracker.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@RequiredArgsConstructor
public class EmployeeRepositoryTest {

	final EmployeeRepository repository;

	@Test
	public void EmpRepo_SaveAll_ReturnSavedEmployee() {
		// Arrange
		Employee employee = Employee
				.builder()
				.email("calin5@yahoo.com")
				.firstName("calin")
				.lastName("calin")
				.id(10)
				.imageData(null)
				.password("calin")
				.recentURLS(null)
				.role(Role.USER)
				.build();

		Employee savedEmployee = repository.save(employee);
		Assertions.assertEquals("calin", savedEmployee.getFirstName());
	}

	@Test
	public void EmpRepo_GetThatEmployee_ReturnSavedEmployee() {
		var employee = Employee
				.builder()
				.email("calin5@yahoo.com")
				.firstName("calin")
				.lastName("calin")
				.id(10)
				.imageData(null)
				.password("calin")
				.recentURLS(null)
				.role(Role.USER)
				.build();

		repository.save(employee);
		var list = repository.findAll();

		System.out.println(list);
		boolean equalsID = list.stream().anyMatch(emp -> emp.getId() == 1);
		Assertions.assertTrue(equalsID);
	}

	@Test
	public void EmpRepo_GetAll_returnsMoreThanOne() {
		var employee = Employee
				.builder()
				.email("calin5@yahoo.com")
				.firstName("calin")
				.lastName("calin")
				.imageData(null)
				.password("calin")
				.recentURLS(null)
				.role(Role.USER)
				.build();

		var employee2 = Employee
				.builder()
				.email("alex@yahoo.com")
				.firstName("radi")
				.lastName("radu")
				.imageData(null)
				.password("robert")
				.recentURLS(null)
				.role(Role.USER)
				.build();

		repository.save(employee);
		repository.save(employee2);

		var emps = repository.findAll();

		Assertions.assertNotNull(emps, "List is null");
		Assertions.assertEquals(2, emps.size());
	}
}
