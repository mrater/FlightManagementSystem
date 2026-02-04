package org.example.Model;

import org.example.Datatypes.Connection;
import org.example.Datatypes.RPassenger;
import org.example.Model.DAO;
import org.example.Model.DepartureFactory;
import org.example.Model.IDAO;
import org.example.Model.IFlight;
import org.example.Model.Model;
import org.example.Model.OperationRegistry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Tag("model")
@Tag("unit")
class ModelTest {

    private static OperationRegistry registry;
    private static IDAO dao;
    private Model model;

    @BeforeAll
    static void setUpBeforeClass() {
        registry = new OperationRegistry();
        dao = new DAO();

        DepartureFactory departureFactory = new DepartureFactory();
        Date now = new Date();
        IFlight flight1 = departureFactory.createFlight("100", now, "SCHEDULED", 1);
        IFlight flight2 = departureFactory.createFlight("200", now, "SCHEDULED", 2);

        registry.addFlight(flight1);
        registry.addFlight(flight2);
    }

    @AfterAll
    static void tearDownAfterClass() {
        registry = null;
        dao = null;
    }

    @BeforeEach
    void setUp() {
        model = new Model(registry, dao);
    }

    @AfterEach
    void tearDown() {
        model = null;
    }

    @Test
    @Tag("reservation")
    @Tag("critical")
    void reserveFlightShouldFailForUnknownFlight() {
        RPassenger passenger = new RPassenger("Jan", "Kowalski");
        boolean result = model.reserveFlight(999, passenger);
        assertFalse(result);
    }

    @Test
    @Tag("reservation")
    void reserveFlightShouldFailForEmptyPassenger() {
        RPassenger passenger = new RPassenger("", "");
        boolean result = model.reserveFlight(100, passenger);
        assertFalse(result);
    }

    @ParameterizedTest
    @Tag("reservation")
    @CsvSource({
            "100,true",
            "200,true",
            "300,false"
    })
    void reserveFlightParameterized(int flightId, boolean expected) {
        RPassenger passenger = new RPassenger("Anna", "Nowak");
        boolean result = model.reserveFlight(flightId, passenger);
        assertEquals(expected, result);
    }

    @Test
    @Tag("search")
    void findFlightsShouldReturnMatchingDeparture() {
        Date now = new Date();
        Connection query = new Connection(null, null, Connection.QUERY_RESERVED_ID, now);
        List<Connection> result = model.findFlights(query);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.stream().anyMatch(c -> c.flightId().equals("100") || c.flightId().equals("200")));
    }

    @Test
    @Tag("security")
    void getUserTokenShouldReturnNullForInvalidCredentials() {
        assertNull(model.getUserToken("", ""));
        assertNull(model.getUserToken(null, "pass"));
    }

    @ParameterizedTest
    @Tag("security")
    @NullAndEmptySource
    @MethodSource("invalidCredentials")
    void getUserTokenParameterizedInvalidCredentials(String login, String password) {
        String token = model.getUserToken(login, password);
        assertNull(token);
    }

    private static Stream<Arguments> invalidCredentials() {
        return Stream.of(
                Arguments.of("user", ""),    // empty password
                Arguments.of("", "secret"),   // empty login
                Arguments.of("", " ")        // both blank
        );
    }
}
