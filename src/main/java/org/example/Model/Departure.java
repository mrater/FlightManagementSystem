package org.example.Model;

import java.util.Date;

public class Departure implements IFlight {
    String flightNumber;
    Date departureTime;
    int gateNumber;
    int runwayNumber;
    String origin;
    String destination;
    String status;

    //TODO implement these methods
    @Override
    public String getFlightNumber() {
        return flightNumber;
    }

    @Override
    public Date getTime() {
        return departureTime;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public String getDestination() {
        return destination;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public void setRunway() {
        // no-op default implementation for now
    }
}
