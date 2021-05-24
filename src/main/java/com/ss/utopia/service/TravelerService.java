package com.ss.utopia.service;

import com.ss.utopia.database.dao.*;
import com.ss.utopia.database.entity.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author NickM13
 * @since 5/21/2021
 */
@SuppressWarnings("ConstantConditions")
public class TravelerService {

    private final ConnectionUtil connUtil = new ConnectionUtil();

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
     * Returns a list of all flight statuses
     *
     * @return list of flight statuses
     */
    public List<FlightStatus> getAllFlightStatuses() {
        Connection connection = null;
        List<FlightStatus> flights = new ArrayList<>();
        try {
            connection = connUtil.getConnection();

            FlightStatusDAO flightStatDao = new FlightStatusDAO(connection);
            flights = flightStatDao.readAllFlights();
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

    public void addBooking(Flight flight, User user, String stripeId) {
        Connection connection = null;
        try {
            connection = connUtil.getConnection();

            Booking booking = new Booking();
            booking.setId(Math.abs(new Random().nextInt()));
            booking.setActive(true);
            booking.generateConfirmationCode();

            BookingDAO bookingDao = new BookingDAO(connection);
            bookingDao.addBooking(booking);

            FlightBookings flightBooking = new FlightBookings();
            flightBooking.setFlightId(flight.getId());
            flightBooking.setBookingId(booking.getId());

            BookingUser bookingUser = new BookingUser();
            bookingUser.setBookingId(booking.getId());
            bookingUser.setUserId(user.getId());

            BookingPayment payment = new BookingPayment();
            payment.setBookingId(booking.getId());
            payment.setRefunded(false);
            payment.setStripeId(stripeId);

            FlightBookingsDAO flightBookDao = new FlightBookingsDAO(connection);
            flightBookDao.addFlightBook(flightBooking);

            BookingUserDAO bookingUserDao = new BookingUserDAO(connection);
            bookingUserDao.addUser(bookingUser);

            BookingPaymentDAO paymentDao = new BookingPaymentDAO(connection);
            paymentDao.addPayment(payment);

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

    public List<Booking> getAllBookings() {
        Connection connection = null;
        List<Booking> bookings = new ArrayList<>();
        try {
            connection = connUtil.getConnection();

            BookingDAO bookingDao = new BookingDAO(connection);
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

    public void cancelTrip(int bookingId) {
        Connection connection = null;
        try {
            connection = connUtil.getConnection();

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
