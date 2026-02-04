package org.example.Datatypes;

import java.util.Date;

public record Connection(String fromId, String destId, String flightId, Date date) {
    public static String QUERY_RESERVED_ID = "__QUERY";
}
