package org.example.Model;

import java.util.Date;

public class Departure implements IFlight {
    String flightNumber;
    Date departureTime;
    int gateNumber;
    int runwayNumber;
    String origin;
    String destination;

    //TODO implement these methods
    @Override
    public String getFlightNumber() {
        return "";
    }

    @Override
    public Date getTime() {
        return null;
    }

    @Override
    public String getStatus() {
        return "";
    }

    public String getDestination() {
        return destination;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public void setRunway() {

    }
}
