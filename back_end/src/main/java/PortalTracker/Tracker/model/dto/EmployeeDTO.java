package PortalTracker.Tracker.model.dto;

import PortalTracker.Tracker.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String username;
}
