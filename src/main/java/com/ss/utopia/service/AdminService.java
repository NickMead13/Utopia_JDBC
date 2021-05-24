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
public class AdminService {

    private final ConnectionUtil connUtil = new ConnectionUtil();

    /**
     * Saves a route to the database
     *
     * @param route route to save
     */
    public void addRoute(Route route) {
        Connection connection = null;
        try {
            connection = connUtil.getConnection();

            RouteDAO routeDao = new RouteDAO(connection);
            routeDao.addRoute(route);

            connection.commit();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables2) {
                throwables2.printStackTrace();
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
     * Deletes a route from the route db table
     *
     * @param route route to delete
     */
    public void deleteRoute(Route route) {
        Connection connection = null;
        try {
            connection = connUtil.getConnection();

            RouteDAO routeDao = new RouteDAO(connection);
            routeDao.deleteRoute(route);

            connection.commit();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables2) {
                throwables2.printStackTrace();
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
     * Returns a list of routes that use airportCode as its origin
     *
     * @param airportCode origin airport
     * @return list of routes originating from airportCode
     */
    public List<Route> getAllRoutesFromOrigin(String airportCode) {
        Connection connection = null;
        List<Route> routes = new ArrayList<>();
        try {
            connection = connUtil.getConnection();

            RouteDAO routeDao = new RouteDAO(connection);
            routes = routeDao.readAllRoutesFromOrigin(airportCode);
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

    /**
     * Returns a list of routes that are destined for airportCode
     *
     * @param airportCode destination airportCode
     * @return list of routes destined for airportCode
     */
    public List<Route> getAllRoutesToDestination(String airportCode) {
        Connection connection = null;
        List<Route> routes = new ArrayList<>();
        try {
            connection = connUtil.getConnection();

            RouteDAO routeDao = new RouteDAO(connection);
            routes = routeDao.readAllRoutesToDestination(airportCode);
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

    /**
     * Returns a list of all airports that do not have a route with airportCode as its origin and other as destination
     *
     * @param airportCode origin airport code
     * @return list of unlinked destination airports
     */
    public List<Airport> getAllAirportsNotOriginatedFrom(String airportCode) {
        Connection connection = null;
        List<Airport> airports = new ArrayList<>();
        try {
            connection = connUtil.getConnection();

            AirportDAO airportDao = new AirportDAO(connection);
            airports = airportDao.readAllAirportsNotOriginatedFrom(airportCode);
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
     * Returns a list of all airports that do not have a route with airportCode as its destination and other as origin
     *
     * @param airportCode destination airport code
     * @return list of unlinked destination airports
     */
    public List<Airport> getAllAirportsNotDestinedTo(String airportCode) {
        Connection connection = null;
        List<Airport> airports = new ArrayList<>();
        try {
            connection = connUtil.getConnection();

            AirportDAO airportDao = new AirportDAO(connection);
            airports = airportDao.readAllAirportsNotDestinedTo(airportCode);
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
     * Returns a list of all airplanes
     *
     * @return list of airplanes
     */
    public List<Airplane> getAllAirplanes() {
        Connection connection = null;
        List<Airplane> airplanes = new ArrayList<>();
        try {
            connection = connUtil.getConnection();

            AirplaneDAO airplaneDao = new AirplaneDAO(connection);
            airplanes = airplaneDao.readAllAirplanes();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return airplanes;
    }

    /**
     * Returns a list of all airplanes that have a minimum of 'seatCount' seats in the airplane type
     *
     * @param seatCount minimal seat count of airplane
     * @return list of airplanes
     */
    public List<Airplane> getAirplanesBySeatCount(int seatCount) {
        Connection connection = null;
        List<Airplane> airplanes = new ArrayList<>();
        try {
            connection = connUtil.getConnection();

            AirplaneDAO airplaneDao = new AirplaneDAO(connection);
            airplanes = airplaneDao.readAirplanesBySeatCount(seatCount);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return airplanes;
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
     * Saves a flight to the database
     *
     * @param flight flight to save
     */
    public void addFlight(Flight flight) {
        Connection connection = null;
        try {
            connection = connUtil.getConnection();

            FlightDAO flightDao = new FlightDAO(connection);
            flightDao.addFlight(flight);

            connection.commit();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables2) {
                throwables2.printStackTrace();
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
     * Updates a flight into the database
     *
     * @param flight flight to update
     */
    public void updateFlight(Flight flight) {
        Connection connection = null;
        try {
            connection = connUtil.getConnection();

            FlightDAO flightDao = new FlightDAO(connection);
            flightDao.updateFlight(flight);

            connection.commit();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables2) {
                throwables2.printStackTrace();
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
     * Deletes a flight from the database
     *
     * @param flight flight to delete
     */
    public void deleteFlight(Flight flight) {
        Connection connection = null;
        try {
            connection = connUtil.getConnection();

            FlightDAO flightDao = new FlightDAO(connection);
            flightDao.deleteFlight(flight);

            connection.commit();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables2) {
                throwables2.printStackTrace();
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
     * Returns a list of users with passed role id
     *
     * @param roleId role id to check against users
     * @return list of users with roleId
     */
    public List<User> getAllUsers(int roleId) {
        Connection connection = null;
        List<User> users = new ArrayList<>();
        try {
            connection = connUtil.getConnection();

            UserDAO userDao = new UserDAO(connection);
            users = userDao.readAllUsers(roleId);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return users;
    }

    /**
     * Saves a user to the database
     *
     * @param user user to save
     */
    public void addUser(User user) {
        Connection connection = null;
        try {
            connection = connUtil.getConnection();

            UserDAO userDao = new UserDAO(connection);
            userDao.addUser(user);

            connection.commit();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables2) {
                throwables2.printStackTrace();
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
     * Updates a user into the database
     *
     * @param user user to update
     */
    public void updateUser(User user) {
        Connection connection = null;
        try {
            connection = connUtil.getConnection();

            UserDAO userDao = new UserDAO(connection);
            userDao.updateUser(user);

            connection.commit();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables2) {
                throwables2.printStackTrace();
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
     * Deletes a user from the database
     *
     * @param user user to delete
     */
    public void deleteUser(User user) {
        Connection connection = null;
        try {
            connection = connUtil.getConnection();

            new UserDAO(connection).deleteUser(user);

            connection.commit();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables2) {
                throwables2.printStackTrace();
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
     * Saves an airport to the database
     *
     * @param airport airport to save
     */
    public void addAirport(Airport airport) {
        Connection connection = null;
        try {
            connection = connUtil.getConnection();

            AirportDAO airportDao = new AirportDAO(connection);
            airportDao.addAirport(airport);

            connection.commit();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables2) {
                throwables2.printStackTrace();
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
     * Updates a airport into the database
     *
     * @param airport airport to update
     */
    public void updateAirport(Airport airport) {
        Connection connection = null;
        try {
            connection = connUtil.getConnection();

            AirportDAO airportDao = new AirportDAO(connection);
            airportDao.updateAirport(airport);

            connection.commit();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables2) {
                throwables2.printStackTrace();
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
     * Deletes an airport from the database
     *
     * @param airport airport to delete
     */
    public void deleteAirport(Airport airport) {
        Connection connection = null;
        try {
            connection = connUtil.getConnection();

            AirportDAO airportDao = new AirportDAO(connection);
            airportDao.deleteAirport(airport);

            connection.commit();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables2) {
                throwables2.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public List<FlightBookings> getAllFlightBookings() {
        Connection connection = null;
        List<FlightBookings> bookings = new ArrayList<>();
        try {
            connection = connUtil.getConnection();

            FlightBookingsDAO bookingDao = new FlightBookingsDAO(connection);
            bookings = bookingDao.readAllBookings();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return bookings;
    }

    public List<UserBooking> getAllUserBookings(User user) {
        Connection connection = null;
        List<UserBooking> bookings = new ArrayList<>();
        try {
            connection = connUtil.getConnection();

            UserBookingDAO bookingDao = new UserBookingDAO(connection);
            bookings = bookingDao.readAllBookingsByUser(user);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return bookings;
    }

    /**
     * Cancels a users trip and sets refunded to true in payments
     *
     * @param bookingId
     */
    public void cancelTrip(int bookingId) {
        Connection connection = null;
        try {
            connection = connUtil.getConnection();

            BookingPaymentDAO paymentDao = new BookingPaymentDAO(connection);
            paymentDao.refundPayment(bookingId);

            BookingDAO bookingDao = new BookingDAO(connection);
            bookingDao.cancelBooking(bookingId);

            connection.commit();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables2) {
                throwables2.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
