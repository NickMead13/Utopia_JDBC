package com.ss.utopia.database.dao;

import com.ss.utopia.database.entity.FlightStatus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public class FlightStatusDAO extends BaseDAO<FlightStatus> {

    public FlightStatusDAO(Connection connection) {
        super(connection);
    }

    /**
     * Returns a list of all flights
     *
     * @return list of flights
     */
    public List<FlightStatus> readAllFlights() {
        return read("SELECT * FROM flight_status", null);
    }

    /**
     * Returns a list of flights from the results of a sql query
     *
     * @param resultSet sql query results
     * @return list of flights
     */
    @Override
    public List<FlightStatus> parseData(ResultSet resultSet) throws SQLException {
        List<FlightStatus> flights = new ArrayList<>();

        while (resultSet.next()) {
            FlightStatus flight = new FlightStatus();
            flight.setId(resultSet.getInt("id"));
            flight.setRouteId(resultSet.getInt("route_id"));
            flight.setAirplaneId(resultSet.getInt("airplane_id"));
            flight.setDepartureTime(resultSet.getTimestamp("departure_time"));
            flight.setReservedSeats(resultSet.getInt("reserved_seats"));
            flight.setSeatPrice(resultSet.getFloat("seat_price"));
            flights.add(flight);
        }

        return flights;
    }
    
}
