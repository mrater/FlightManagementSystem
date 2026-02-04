package org.example.Model;

import org.example.Datatypes.Connection;
import org.example.Datatypes.RPassenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Model implements IModel{

    List<IFlight> flights;

    public List<IFlight> getDeparturesByQuery(String sql_query){
        return List.of();
    }

    @Override
    public String getUserToken(String login, String password) {
        return "";
    }

    List<IFlight> queryFlightsFrom(Connection query){
        List<IFlight> result = new ArrayList<>();
        for (IFlight flight : flights){
            Departure departure = (Departure) flight;
            if (query.date() != flight.getTime()) continue;
            else if (!Objects.equals(query.destId(), departure.getOrigin())) continue;
            

        }
    }

    @Override
    public List<Connection> findFlights(Connection query) {
        // return IFlights list that is queried
        // via SQL with dates from this query and appropriate dest and origin
        List<Departure> flightsByQuery =
        List<Connection> result = new ArrayList<>();
        for (Departure flight : flightsByQuery){
            Connection connection = new Connection(flight.getFlightNumber(),
                    flight.getOrigin(), flight.getDestination(),
                    flight.getTime());
        }

    }

    @Override
    public boolean reserveFlight(int flightId, RPassenger user) {
        return false;
    }
}
