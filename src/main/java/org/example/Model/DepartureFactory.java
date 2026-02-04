package org.example.Model;

import java.util.Date;

public class DepartureFactory implements FlightFactory {
    @Override
    public IFlight createFlight(String flightNumber, Date time, String status, int runwayNumber) {
        Departure departure = new Departure();
        departure.flightNumber = flightNumber;
        departure.departureTime = time;
        departure.runwayNumber = runwayNumber;
        departure.status = status;
        return departure;
    }
}
