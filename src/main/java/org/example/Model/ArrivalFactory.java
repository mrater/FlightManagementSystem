package org.example.Model;

import java.util.Date;

public class ArrivalFactory implements FlightFactory {
    @Override
    public IFlight createFlight(String flightNumber, Date time, String status, int runwayNumber) {
        return new Arrival(flightNumber, time, 0, runwayNumber, null);
    }
}
