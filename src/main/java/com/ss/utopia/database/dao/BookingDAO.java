package com.ss.utopia.database.dao;

import com.ss.utopia.database.entity.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class BookingDAO extends BaseDAO<Booking> {

    public BookingDAO(Connection connection) {
        super(connection);
    }

    public void addBooking(Booking booking) {
        int nextId = update("INSERT INTO booking " +
                        "(is_active, confirmation_code) values (?, ?)",
                new Object[] {booking.getActive(), booking.getConfirmationCode()});
        booking.setId(nextId);
    }

    public void cancelBooking(int bookingId) {
        update("UPDATE booking SET is_active = ? WHERE id = ?",
                new Object[] {false, bookingId});
    }

    public List<Booking> readAllBookings() {
        return read("SELECT * FROM booking", null);
    }

    @Override
    public List<Booking> parseData(ResultSet resultSet) throws SQLException {
        List<Booking> bookings = new ArrayList<>();

        while (resultSet.next()) {
            Booking booking = new Booking();
            booking.setId(resultSet.getInt("id"));
            booking.setActive(resultSet.getBoolean("is_active"));
            booking.setConfirmationCode(resultSet.getString("confirmation_code"));
            bookings.add(booking);
        }

        return bookings;
    }

}
