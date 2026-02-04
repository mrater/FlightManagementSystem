package org.example.Controller;

import org.example.Datatypes.Connection;

import java.util.List;

public interface IControllerPassenger {
    List<Connection> searchConnection(Connection query);

    boolean commitReservation(String firstName, String lastName, int flightId);
}
