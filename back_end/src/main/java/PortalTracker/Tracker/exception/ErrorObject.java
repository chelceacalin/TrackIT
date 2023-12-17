package PortalTracker.Tracker.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {

    Integer statusCode;
    String message;
    Date timestamp;
}
