package org.example.Model;

import java.util.Date;

public interface IFlight {
    String getFlightNumber();
    Date getTime();
    String getStatus();
    void setRunway();
}
