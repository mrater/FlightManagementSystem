package org.example.Model;

import java.util.Date;

public class Arrival implements IFlight {
    private String flightNumber;
    private Date landingTime;
    private int gateNumber;
    private int runwayNumber;
    private String origin;

    public Arrival(String flightNumber, Date landingTime, int gateNumber, int runwayNumber, String origin) {
        this.flightNumber = flightNumber;
        this.landingTime = landingTime;
        this.gateNumber = gateNumber;
        this.runwayNumber = runwayNumber;
        this.origin = origin;
    }

    @Override
    public String getFlightNumber() {
        return flightNumber;
    }

    @Override
    public Date getTime() {
        return landingTime;
    }

    @Override
    public String getStatus() {
        return null;
    }

    @Override
    public void setRunway() {
        // no-op default implementation for now
    }

    public String getOrigin() {
        return origin;
    }
}
