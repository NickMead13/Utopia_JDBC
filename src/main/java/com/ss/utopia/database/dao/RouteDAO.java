package com.ss.utopia.database.dao;

import com.ss.utopia.database.entity.Airport;
import com.ss.utopia.database.entity.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class RouteDAO extends BaseDAO<Route> {

    public RouteDAO(Connection connection) {
        super(connection);
    }

    public void addRoute(Route route) {
        update("INSERT INTO route (origin_id, destination_id) VALUES (?, ?)",
                new Object[] {route.getOrigin().getAirportCode(),
                        route.getDestination().getAirportCode()});
    }

    public void updateRoute(Route route) {
        update("UPDATE route SET origin_id = ?, destination_id = ? WHERE id = ?",
                new Object[] {route.getOrigin().getAirportCode(),
                        route.getDestination().getAirportCode(),
                        route.getId()});
    }

    public void deleteRoute(Route route) {
        update("DELETE FROM route WHERE id = ?",
                new Object[] {route.getId()});
    }

    public Route readRoute(String originCode, String destinCode) {
        List<Route> results = read("SELECT route.id, o.origin_id, o.origin_city, d.destination_id, d.destination_city " +
                        "FROM route " +
                        "INNER JOIN (SELECT iata_id AS origin_id, city AS origin_city FROM airport) AS o ON o.origin_id = route.origin_id " +
                        "INNER JOIN (SELECT iata_id AS destination_id, city AS destination_city FROM airport) AS d ON d.destination_id = route.destination_id " +
                        "WHERE o.origin_id = ? AND d.destination_id = ?",
                new Object[]{originCode, destinCode});
        if (results.isEmpty()) return null;
        return results.get(0);
    }

    /**
     * Returns a list of routes that use airportCode as their destination airport code
     *
     * @param airportCode destination airport code
     * @return list of routes destined for airportCode
     */
    public List<Route> readAllRoutesToDestination(String airportCode) {
        return read("SELECT route.id, o.origin_id, o.origin_city, d.destination_id, d.destination_city " +
                        "FROM route " +
                        "INNER JOIN (SELECT iata_id AS origin_id, city AS origin_city FROM airport) AS o ON o.origin_id = route.origin_id " +
                        "INNER JOIN (SELECT iata_id AS destination_id, city AS destination_city FROM airport) AS d ON d.destination_id = route.destination_id " +
                        "WHERE d.destination_id = ?",
                new Object[] {airportCode});
    }

    /**
     * Returns a list of routes that use airportCode as their origin airport code
     *
     * @param airportCode origin airport code
     * @return list of routes originating from airportCode
     */
    public List<Route> readAllRoutesFromOrigin(String airportCode) {
        return read("SELECT route.id, o.origin_id, o.origin_city, d.destination_id, d.destination_city " +
                        "FROM route " +
                        "INNER JOIN (SELECT iata_id AS origin_id, city AS origin_city FROM airport) AS o ON o.origin_id = route.origin_id " +
                        "INNER JOIN (SELECT iata_id AS destination_id, city AS destination_city FROM airport) AS d ON d.destination_id = route.destination_id " +
                        "WHERE o.origin_id = ?",
                new Object[] {airportCode});
    }

    public List<Route> readAllRoutes() {
        return read("SELECT route.id, o.origin_id, o.origin_city, d.destination_id, d.destination_city " +
                "FROM route " +
                "INNER JOIN (SELECT iata_id AS origin_id, city AS origin_city FROM airport) AS o ON o.origin_id = route.origin_id " +
                "INNER JOIN (SELECT iata_id AS destination_id, city AS destination_city FROM airport) AS d ON d.destination_id = route.destination_id", null);
    }

    public List<Route> readAllRoutesByAirportCode(String airportCode) {
        return read("SELECT * FROM route WHERE origin_id = ? OR destination_id = ?",
                new Object[] {airportCode, airportCode});
    }

    @Override
    public List<Route> parseData(ResultSet resultSet) throws SQLException {
        List<Route> routes = new ArrayList<>();
        while (resultSet.next()) {
            Route route = new Route();
            route.setId(resultSet.getInt("id"));
            route.getOrigin().setAirportCode(resultSet.getString("origin_id"));
            route.getOrigin().setCityName(resultSet.getString("origin_city"));
            route.getDestination().setAirportCode(resultSet.getString("destination_id"));
            route.getDestination().setCityName(resultSet.getString("destination_city"));
            routes.add(route);
        }
        return routes;
    }

}
