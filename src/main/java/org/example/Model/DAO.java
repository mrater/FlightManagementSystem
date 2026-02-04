package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class DAO implements IDAO {

    private final List<String> eventLog = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();
    private final List<IFlight> flights = new ArrayList<>();
    private final List<Aircraft> aircrafts = new ArrayList<>();

    public DAO() {
        employees.add(new Employee("John", "Doe", "user", Role.ADMIN));
    }

    @Override
    public void saveEventLogEntry(String user, String string) {
        eventLog.add(user + ": " + string);
    }

    @Override
    public void loadSchedule() {
        // no-op placeholder: in a real implementation this would load flights
    }

    @Override
    public void saveNewFlight(IFlight flight) {
        if (flight != null) {
            flights.add(flight);
        }
    }

    @Override
    public void updateFlight(IFlight flight, String data, String value) {
        if (flight == null) {
            return;
        }
        saveEventLogEntry("system", "updateFlight called for " + flight.getFlightNumber());
    }

    @Override
    public void loadAircrafts(Aircraft[] aircrafts) {
        this.aircrafts.clear();
        if (aircrafts == null) {
            return;
        }
        for (Aircraft aircraft : aircrafts) {
            if (aircraft != null) {
                this.aircrafts.add(aircraft);
            }
        }
    }

    @Override
    public Employee[] getUsers(String string) {
        if (string == null) {
            return new Employee[0];
        }
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (string.equals(employee.getLogin())) {
                result.add(employee);
            }
        }
        return result.toArray(new Employee[0]);
    }
}
