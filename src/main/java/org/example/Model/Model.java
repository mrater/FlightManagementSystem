package org.example.Model;

import org.example.Datatypes.Connection;
import org.example.Datatypes.RPassenger;

import java.util.ArrayList;
import java.util.List;

public class Model implements IModel {

    private final OperationRegistry registry;
    private final IDAO dao;

    public Model() {
        this(new OperationRegistry(), new DAO());
    }

    public Model(OperationRegistry registry, IDAO dao) {
        this.registry = registry;
        this.dao = dao;
    }

    @Override
    public String getUserToken(String login, String password) {
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            return null;
        }

        Employee[] users = dao.getUsers(login);
        if (users == null || users.length == 0) {
            return null;
        }

        String token = login + "-token";
        dao.saveEventLogEntry(login, "User authenticated");
        return token;
    }

    @Override
    public List<Connection> findFlights(Connection query) {
        if (query == null) {
            return new ArrayList<>();
        }

        List<Connection> result = new ArrayList<>();
        for (IFlight flight : registry.getAllFlights()) {
            if (flight instanceof Departure) {
                Departure departure = (Departure) flight;

                boolean originMatches = query.fromId() == null
                        || query.fromId().isEmpty()
                        || query.fromId().equals(departure.getOrigin());
                boolean destMatches = query.destId() == null
                        || query.destId().isEmpty()
                        || query.destId().equals(departure.getDestination());
                boolean dateMatches = query.date() == null
                        || query.date().equals(departure.getTime());

                if (originMatches && destMatches && dateMatches) {
                    result.add(new Connection(
                            departure.getOrigin(),
                            departure.getDestination(),
                            departure.getFlightNumber(),
                            departure.getTime()));
                }
            } else if (flight instanceof Arrival) {
                Arrival arrival = (Arrival) flight;

                boolean originMatches = query.fromId() == null
                        || query.fromId().isEmpty()
                        || query.fromId().equals(arrival.getOrigin());
                boolean dateMatches = query.date() == null
                        || query.date().equals(arrival.getTime());

                if (originMatches && dateMatches) {
                    result.add(new Connection(
                            arrival.getOrigin(),
                            query.destId(),
                            arrival.getFlightNumber(),
                            arrival.getTime()));
                }
            }
        }

        return result;
    }

    @Override
    public boolean reserveFlight(int flightId, RPassenger user) {
        if (user == null || user.firstName().isEmpty() || user.lastName().isEmpty()) {
            return false;
        }

        IFlight flight = registry.findFlight(flightId);
        if (flight == null) {
            return false;
        }

        String userName = user.firstName() + " " + user.lastName();
        dao.saveEventLogEntry(userName, "Reserved flight " + flight.getFlightNumber());
        return true;
    }
}
