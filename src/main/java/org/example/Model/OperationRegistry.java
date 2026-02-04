package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class OperationRegistry {
    private final List<Aircraft> aircrafts;
    private final List<IFlight> flights;
    private final List<Employee> employees = new ArrayList<>();

    public OperationRegistry() {
        this.aircrafts = new ArrayList<>();
        this.flights = new ArrayList<>();
    }

    public void addAircraft(Aircraft aircraft) {
        if (aircraft == null) {
            throw new IllegalArgumentException("aircraft cannot be null");
        }
        this.aircrafts.add(aircraft);
    }

    public void addFlight(IFlight flight) {
        if (flight == null) {
            throw new IllegalArgumentException("flight cannot be null");
        }
        this.flights.add(flight);
    }

    public Aircraft findAircraft(String registration) {
        if (registration == null) {
            return null;
        }
        for (Aircraft aircraft : aircrafts) {
            if (registration.equals(aircraft.getRegistration())) {
                return aircraft;
            }
        }
        return null;
    }

    public IFlight findFlight(int flightId) {
        String idAsString = String.valueOf(flightId);
        for (IFlight flight : flights) {
            if (idAsString.equals(flight.getFlightNumber())) {
                return flight;
            }
        }
        return null;
    }

    public List<IFlight> getAllFlights() {
        return new ArrayList<>(flights);
    }
}
