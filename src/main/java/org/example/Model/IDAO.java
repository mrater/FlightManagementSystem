package org.example.Model;

public interface IDAO {
    void saveEventLogEntry(String user, String string);

    void loadSchedule();

    void saveNewFlight(IFlight flight);

    void updateFlight(IFlight flight, String data, String value);

    void loadAircrafts(Aircraft[] aircrafts);

    Employee[] getUsers(String string);
}
