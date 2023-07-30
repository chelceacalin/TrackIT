package PortalTracker.Tracker.api.repository;

import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.Role;
import PortalTracker.Tracker.repository.EmployeeRepository;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.Role;
import PortalTracker.Tracker.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
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
                .email("calin5@yahoo.com")
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
        // Arrange
        // Act
        var list=repository.findAll();

        System.out.println(list);
        boolean equalsID=list.stream().anyMatch(emp->emp.getId()==1);
        // Assert
        //Assertions.assertNotNull("Employee is null", savedemp);
        Assertions.assertEquals(true, equalsID);
    }

    @Test
    public void EmpRepo_GetAll_returnsMoreThanOne(){
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

        var emps=repository.findAll();

        Assertions.assertNotNull(emps,"List is null");
        Assertions.assertEquals(2, emps.size());
    }
}
