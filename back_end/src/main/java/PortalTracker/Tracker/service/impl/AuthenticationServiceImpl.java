package PortalTracker.Tracker.service.impl;

import PortalTracker.Tracker.dao.request.SignUpRequest;
import PortalTracker.Tracker.dao.request.SigninRequest;
import PortalTracker.Tracker.dao.response.JwtAuthenticationResponse;
import PortalTracker.Tracker.model.Employee;
import PortalTracker.Tracker.model.Role;
import PortalTracker.Tracker.repository.EmployeeRepository;
import PortalTracker.Tracker.service.AuthenticationService;
import PortalTracker.Tracker.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    final EmployeeRepository employeeRepository;
    final PasswordEncoder passwordEncoder;
    final JwtService jwtService;
    final AuthenticationManager manager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        employeeRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = employeeRepository.findEmployeeByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
