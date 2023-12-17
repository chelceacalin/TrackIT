package PortalTracker.Tracker.exception;

public class EntityNotFoundException extends RuntimeException {
	static final long serialVersionUID = 1;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
