package org.example.Model;

import org.example.Datatypes.Connection;
import org.example.Datatypes.RPassenger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ModelMockitoTest {

    @Mock
    private OperationRegistry registry;

    @Mock
    private IDAO dao;

    @InjectMocks
    private Model model;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @ParameterizedTest
    @CsvSource({
            "user,true",
            "nouser,false"
    })
    void getUserTokenUsesDaoAndReturnsTokenAccordingToUsers(String login, boolean hasUser) {
        Employee[] users = hasUser
                ? new Employee[]{new Employee("Jan", "Kowalski", login, Role.ADMIN)}
                : new Employee[0];

        when(dao.getUsers(login)).thenReturn(users);
        // explicit behavior for void method
        doNothing().when(dao).saveEventLogEntry(anyString(), anyString());

        String token = model.getUserToken(login, "password");

        if (hasUser) {
            assertNotNull(token);
            assertTrue(token.contains(login));
            verify(dao, times(1)).saveEventLogEntry(eq(login), anyString());
        } else {
            assertNull(token);
            verify(dao, never()).saveEventLogEntry(anyString(), anyString());
        }
    }

    @Test
    void reserveFlightLogsAndReturnsTrueWhenFlightExists() {
        int flightId = 100;
        IFlight flight = mock(IFlight.class);
        when(registry.findFlight(flightId)).thenReturn(flight);
        when(flight.getFlightNumber()).thenReturn("100");

        RPassenger passenger = new RPassenger("Anna", "Nowak");

        boolean result = model.reserveFlight(flightId, passenger);

        assertTrue(result);

        InOrder inOrder = inOrder(registry, dao);
        inOrder.verify(registry).findFlight(flightId);
        inOrder.verify(dao).saveEventLogEntry(eq("Anna Nowak"), contains("100"));
    }

    @Test
    void reserveFlightReturnsFalseWhenFlightMissing() {
        when(registry.findFlight(anyInt())).thenReturn(null);

        RPassenger passenger = new RPassenger("Adam", "Nowak");
        boolean result = model.reserveFlight(999, passenger);

        assertFalse(result);
        verify(dao, never()).saveEventLogEntry(anyString(), anyString());
    }

    @Test
    void reserveFlightPropagatesExceptionWhenLoggingFails() {
        int flightId = 100;
        IFlight flight = mock(IFlight.class);
        when(registry.findFlight(flightId)).thenReturn(flight);
        when(flight.getFlightNumber()).thenReturn("100");

        doThrow(new RuntimeException("log failed")).when(dao).saveEventLogEntry(anyString(), anyString());

        RPassenger passenger = new RPassenger("Piotr", "Zielinski");

        assertThrows(RuntimeException.class, () -> model.reserveFlight(flightId, passenger));
    }

    @Test
    void findFlightsThrowsWhenRegistryFails() {
        Connection query = new Connection(null, null, Connection.QUERY_RESERVED_ID, new Date());
        when(registry.getAllFlights()).thenThrow(new IllegalStateException("registry error"));

        assertThrows(IllegalStateException.class, () -> model.findFlights(query));
    }

    @Test
    void findFlightsReturnsEmptyListWhenNoFlights() {
        Connection query = new Connection(null, null, Connection.QUERY_RESERVED_ID, new Date());
        when(registry.getAllFlights()).thenReturn(Collections.emptyList());

        List<Connection> result = model.findFlights(query);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(registry, atLeastOnce()).getAllFlights();
    }
}
