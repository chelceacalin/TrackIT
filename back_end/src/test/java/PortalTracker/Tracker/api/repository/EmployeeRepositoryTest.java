package PortalTracker.Tracker.api.repository;

import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.Role;
import PortalTracker.Tracker.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.Role;
import PortalTracker.Tracker.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository) {
        this.repository = employeeRepository;
    }


    @Test
    public void EmpRepo_SaveAll_ReturnSavedEmployee() {
        // Arrange
        Employee employee = Employee
                .builder()
                .email("calin4@yahoo.com")
                .firstName("calin")
                .lastName("calin")
                .id(10)
                .imageData(null)
                .password("calin")
                .recentURLS(null)
                .role(Role.USER)
                .build();

        // Act
        Employee savedemp = repository.save(employee);

        // Assert
        //Assertions.assertNotNull("Employee is null", savedemp);
        Assertions.assertEquals("calin", savedemp.getFirstName());
    }
}
