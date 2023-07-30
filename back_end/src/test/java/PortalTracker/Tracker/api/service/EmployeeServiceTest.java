package PortalTracker.Tracker.api.service;


import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.Role;
import PortalTracker.Tracker.model.dto.EmployeeDTO;
import PortalTracker.Tracker.repository.EmployeeRepository;
import PortalTracker.Tracker.service.EmployeeService;
import PortalTracker.Tracker.service.impl.EmployeeServiceImpl;
import jakarta.inject.Inject;
import org.h2.bnf.context.DbTableOrView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;

    @Test
    public void EmployeeService_Create_Employee_ReturnsEmployeeDTO(){
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



        when(repository.save(Mockito.any(Employee.class))).thenReturn(employee2);
        Employee em=service.createEmployee(employee2);

        Assertions.assertNotNull(em);

    }

}
