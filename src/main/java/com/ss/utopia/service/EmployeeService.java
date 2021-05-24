package com.ss.utopia.service;

import com.ss.utopia.database.dao.*;
import com.ss.utopia.database.entity.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NickM13
 * @since 5/21/2021
 */
@SuppressWarnings("ConstantConditions")
public class EmployeeService {

    private final ConnectionUtil connUtil = new ConnectionUtil();

    /**
     * Returns a list of all flights
     *
     * @return list of flights
     */
    public List<Flight> getAllFlights() {
        Connection connection = null;
        List<Flight> flights = new ArrayList<>();
        try {
            connection = connUtil.getConnection();

            FlightDAO flightDao = new FlightDAO(connection);
            flights = flightDao.readAllFlights();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return flights;
    }

    public void updateFlight(Flight flight) {
        Connection connection = null;
        try {
            connection = connUtil.getConnection();

            FlightDAO flightDao = new FlightDAO(connection);
            flightDao.updateFlight(flight);

            connection.commit();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * Returns a list of all airports
     *
     * @return light of airports
     */
    public List<Airport> getAllAirports() {
        Connection connection = null;
        List<Airport> airports = new ArrayList<>();
        try {
            connection = connUtil.getConnection();

            AirportDAO airportDao = new AirportDAO(connection);
            airports = airportDao.readAllAirports();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return airports;
    }

    /**
     * Returns one route with origin and destination airports. Useful for checking if a route exists or not.
     *
     * @param originAirportCode origin airport code
     * @param destinAirportCode destination airport code
     * @return route, or null if DNE
     */
    public Route getRoute(String originAirportCode, String destinAirportCode) {
        Connection connection = null;
        Route route = null;
        try {
            connection = connUtil.getConnection();

            RouteDAO routeDao = new RouteDAO(connection);
            route = routeDao.readRoute(originAirportCode, destinAirportCode);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return route;
    }

    /**
     * Returns a list of all routes
     *
     * @return list of routes
     */
    public List<Route> getAllRoutes() {
        Connection connection = null;
        List<Route> routes = new ArrayList<>();
        try {
            connection = connUtil.getConnection();

            RouteDAO routeDao = new RouteDAO(connection);
            routes = routeDao.readAllRoutes();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return routes;
    }

    public User getUserByLogin(String username, String password) {
        Connection connection = null;
        User user = null;
        try {
            connection = connUtil.getConnection();

            UserDAO userDao = new UserDAO(connection);
            user = userDao.readUserByLogin(username, password);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }

}
