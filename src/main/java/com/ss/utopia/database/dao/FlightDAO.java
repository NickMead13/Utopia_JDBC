package com.ss.utopia.database.dao;

import com.ss.utopia.database.entity.Flight;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class FlightDAO extends BaseDAO<Flight> {

    public FlightDAO(Connection connection) {
        super(connection);
    }

    /**
     * Returns a list of all flights
     *
     * @return list of flights
     */
    public List<Flight> readAllFlights() {
        return read("SELECT * FROM flight", null);
    }

    /**
     * Adds a flight to the flight db table
     *
     * @param flight flight to add
     */
    public void addFlight(Flight flight) {
        update("INSERT INTO flight " +
                        "(id, route_id, airplane_id, departure_time, reserved_seats, seat_price) values (?, ?, ?, ?, ?, ?)",
                new Object[] {flight.getId(), flight.getRouteId(), flight.getAirplaneId(), flight.getDepartureTime(), flight.getReservedSeats(), flight.getSeatPrice()});
    }

    /**
     * Updates a flight in the flight db table
     *
     * @param flight flight to update
     */
    public void updateFlight(Flight flight) {
        update("UPDATE flight SET " +
                        "route_id = ?, " +
                        "airplane_id = ?, " +
                        "departure_time = ?, " +
                        "reserved_seats = ?, " +
                        "seat_price = ? " +
                        "WHERE flight.id = ?",
                new Object[] {flight.getRouteId(), flight.getAirplaneId(), flight.getDepartureTime(), flight.getReservedSeats(), flight.getSeatPrice(), flight.getId()});
    }

    /**
     * Flight to delete from the flight db table
     *
     * @param flight flight to delete
     */
    public void deleteFlight(Flight flight) {
        update("DELETE FROM flight WHERE flight.id = ?",
                new Object[] {flight.getId()});
    }

    /**
     * Returns a list of flights from the results of a sql query
     *
     * @param resultSet sql query results
     * @return list of flights
     */
    @Override
    public List<Flight> parseData(ResultSet resultSet) throws SQLException {
        List<Flight> flights = new ArrayList<>();

        while (resultSet.next()) {
            Flight flight = new Flight();
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
