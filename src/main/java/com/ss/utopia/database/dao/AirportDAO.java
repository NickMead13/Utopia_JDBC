package com.ss.utopia.database.dao;

import com.ss.utopia.database.entity.Airport;
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
public class AirportDAO extends BaseDAO<Airport> {

    public AirportDAO(Connection connection) {
        super(connection);
    }

    /**
     *
     *
     * @return
     */
    public List<Airport> readAllAirports() {
        return read("SELECT * FROM airport", null);
    }

    /**
     * Adds an airport to the airport db table
     *
     * @param airport airport to save
     */
    public void addAirport(Airport airport) {
        int lastId = update("INSERT INTO airport " +
                        "(iata_id, city) values (?, ?)",
                new Object[] {airport.getAirportCode(), airport.getCityName()});
    }

    /**
     * Updates an airport in the airport db table
     *
     * @param airport airport to update
     */
    public void updateAirport(Airport airport) {
        update("UPDATE airport SET " +
                        "city = ?" +
                        "WHERE iata_id = ?",
                new Object[] {airport.getCityName(), airport.getAirportCode()});
    }

    /**
     * Deletes an airport from the airport db table
     *
     * @param airport airport to delete
     */
    public void deleteAirport(Airport airport) {
        update("DELETE FROM airport WHERE iata_id = ?",
                new Object[] {airport.getAirportCode()});
    }

    /**
     * Returns a list of airports which do not have a route connecting to destination airport code
     *
     * @param airportCode destination airport code
     * @return list of airports not destined to airport code
     */
    public List<Airport> readAllAirportsNotDestinedTo(String airportCode) {
        return read("SELECT * FROM airport " +
                        "WHERE airport.iata_id NOT IN (" +
                        "SELECT airport.iata_id FROM route " +
                        "INNER JOIN airport " +
                        "ON airport.iata_id = route.origin_id " +
                        "WHERE route.destination_id = ? " +
                        ") AND airport.iata_id != ?",
                new Object[] {airportCode, airportCode});
    }

    /**
     * Returns a list of airports which do not have a route connecting to origin airport code
     *
     * @param airportCode origination airport code
     * @return list of airports not originated from airport code
     */
    public List<Airport> readAllAirportsNotOriginatedFrom(String airportCode) {
        return read("SELECT * FROM airport " +
                        "WHERE airport.iata_id NOT IN (" +
                        "SELECT airport.iata_id FROM route " +
                        "INNER JOIN airport " +
                        "ON airport.iata_id = route.destination_id " +
                        "WHERE route.origin_id = ? " +
                        ") AND airport.iata_id != ?",
                new Object[] {airportCode, airportCode});
    }

    /**
     * Returns a list of airports parsed from a result set of a sql query
     *
     * @param resultSet sql query results
     * @return list of airports
     */
    @Override
    public List<Airport> parseData(ResultSet resultSet) throws SQLException {
        List<Airport> airports = new ArrayList<>();

        while (resultSet.next()) {
            Airport airport = new Airport();
            airport.setAirportCode(resultSet.getString("iata_id"));
            airport.setCityName(resultSet.getString("city"));
            airports.add(airport);
        }

        return airports;
    }

}
