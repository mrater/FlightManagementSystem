package org.example.Controller;

import org.example.Datatypes.Connection;
import org.example.Datatypes.RPassenger;
import org.example.Model.IModel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ControllerPassengerMockitoTest {

    @Mock
    private IModel model;

    @InjectMocks
    private ControllerPassenger controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void searchConnectionDoesNotCallModelForInvalidQuery() {
        Connection invalid = new Connection("A", "B", "NOT_QUERY", new Date());

        List<Connection> result = controller.searchConnection(invalid);

        assertNull(result);
        verify(model, never()).findFlights(any());
    }

    @Test
    void searchConnectionDelegatesToModelForValidQuery() {
        Connection query = new Connection("A", "B", Connection.QUERY_RESERVED_ID, new Date());
        List<Connection> expected = Collections.singletonList(query);

        when(model.findFlights(query)).thenReturn(expected);

        List<Connection> result = controller.searchConnection(query);

        assertSame(expected, result);
        verify(model, times(1)).findFlights(query);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "Jan"})
    void commitReservationValidatesFirstNameAndUsesModel(String firstName) {
        when(model.reserveFlight(anyInt(), any(RPassenger.class))).thenReturn(true);

        boolean result = controller.commitReservation(firstName, "Kowalski", 123);

        if (firstName == null || firstName.isBlank()) {
            assertFalse(result);
            verify(model, never()).reserveFlight(anyInt(), any());
        } else {
            assertTrue(result);
            verify(model, atLeastOnce()).reserveFlight(eq(123), any(RPassenger.class));
        }
    }

    @Test
    void commitReservationPassesCorrectPassengerToModel() {
        when(model.reserveFlight(anyInt(), any(RPassenger.class))).thenReturn(true);

        boolean result = controller.commitReservation("Anna", "Nowak", 321);

        assertTrue(result);

        ArgumentCaptor<RPassenger> captor = ArgumentCaptor.forClass(RPassenger.class);
        verify(model).reserveFlight(eq(321), captor.capture());
        RPassenger captured = captor.getValue();
        assertEquals("Anna", captured.firstName());
        assertEquals("Nowak", captured.lastName());
    }
}
