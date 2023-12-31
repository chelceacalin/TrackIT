package PortalTracker.Tracker.service;

import PortalTracker.Tracker.dao.request.SignUpRequest;
import PortalTracker.Tracker.dao.request.SigninRequest;
import PortalTracker.Tracker.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
	JwtAuthenticationResponse signup(SignUpRequest request);

	JwtAuthenticationResponse signin(SigninRequest request);
}