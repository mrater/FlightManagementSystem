package org.example.Controller;

import org.example.Datatypes.Connection;
import org.example.Datatypes.RPassenger;
import org.example.Model.IModel;

import java.util.List;
import java.util.Objects;

public class ControllerPassenger implements IControllerPassenger{
    IModel model;

    @Override
    public List<Connection> searchConnection(Connection query) {
        // ignore invalid requests
        if (!Objects.equals(query.flightId(), Connection.QUERY_RESERVED_ID)){
            return null;
        }
        return this.model.findFlights(query);
    }

    @Override
    public boolean commitReservation(String firstName, String lastName, int flightId) {
        if (firstName.isEmpty() || lastName.isEmpty()) return false;
        RPassenger passenger = new RPassenger(firstName, lastName);
        return this.model.reserveFlight(flightId, passenger);
    }


}
