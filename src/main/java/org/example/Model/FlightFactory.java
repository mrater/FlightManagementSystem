package org.example.Model;

import java.util.Date;

public interface FlightFactory {
    IFlight createFlight(String flightNumber, Date time, String status, int runwayNumber);
}
