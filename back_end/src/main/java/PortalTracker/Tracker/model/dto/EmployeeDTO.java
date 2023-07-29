package PortalTracker.Tracker.model.dto;

import PortalTracker.Tracker.model.Role;
import lombok.Data;

@Data
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String username;
}
