package PortalTracker.Tracker.dto;

import PortalTracker.Tracker.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    String username;
    String firstName;
    String lastName;
    String password;
    String email;
    Role role;
}
