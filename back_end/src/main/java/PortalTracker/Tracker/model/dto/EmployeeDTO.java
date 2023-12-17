package PortalTracker.Tracker.model.dto;

import PortalTracker.Tracker.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    String firstName;
    String lastName;
    String email;
    String password;
    Role role;
    String username;
}
