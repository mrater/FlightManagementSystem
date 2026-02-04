package org.example.Model;

import org.example.Datatypes.Connection;
import org.example.Datatypes.RPassenger;

import java.util.List;

public interface IModel {
    String getUserToken(String login, String password);

    // for a matching query return matching flights
    List<Connection> findFlights(Connection query);
    boolean reserveFlight(int flightId, RPassenger user);
}
