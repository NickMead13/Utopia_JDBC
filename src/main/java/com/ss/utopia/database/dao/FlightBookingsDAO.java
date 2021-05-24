package com.ss.utopia.database.dao;

import com.ss.utopia.database.entity.Airplane;
import com.ss.utopia.database.entity.AirplaneType;
import com.ss.utopia.database.entity.Booking;
import com.ss.utopia.database.entity.FlightBookings;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class FlightBookingsDAO extends BaseDAO<FlightBookings> {

    public FlightBookingsDAO(Connection connection) {
        super(connection);
    }

    public void addFlightBook(FlightBookings flightBookings) {
        update("INSERT INTO flight_bookings " +
                        "(flight_id, booking_id) values (?, ?)",
                new Object[] {flightBookings.getFlightId(), flightBookings.getBookingId()});
    }

    public List<FlightBookings> readAllBookings() {
        return read("SELECT * FROM flight_bookings", null);
    }

    @Override
    public List<FlightBookings> parseData(ResultSet resultSet) throws SQLException {
        List<FlightBookings> flightBookings = new ArrayList<>();

        while (resultSet.next()) {
            FlightBookings booking = new FlightBookings();
            booking.setFlightId(resultSet.getInt("flight_id"));
            booking.setBookingId(resultSet.getInt("booking_id"));
            flightBookings.add(booking);
        }

        return flightBookings;
    }

}
