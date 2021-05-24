package com.ss.utopia.database.dao;

import com.ss.utopia.database.entity.FlightStatus;
import com.ss.utopia.database.entity.User;
import com.ss.utopia.database.entity.UserBooking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public class UserBookingDAO extends BaseDAO<UserBooking> {

    public UserBookingDAO(Connection connection) {
        super(connection);
    }

    /**
     * Returns a list of all bookings
     *
     * @return list of bookings
     */
    public List<UserBooking> readAllBookings() {
        return read("SELECT * FROM user_booking", null);
    }

    public List<UserBooking> readAllBookingsByUser(User user) {
        return read("SELECT * FROM user_booking WHERE user_id = ?",
                new Object[] {user.getId()});
    }

    /**
     * Returns a list of bookings from the results of a sql query
     *
     * @param resultSet sql query results
     * @return list of bookings
     */
    @Override
    public List<UserBooking> parseData(ResultSet resultSet) throws SQLException {
        List<UserBooking> bookings = new ArrayList<>();

        while (resultSet.next()) {
            UserBooking booking = new UserBooking();
            booking.setId(resultSet.getInt("id"));
            booking.setActive(resultSet.getBoolean("is_active"));
            booking.setConfirmationCode(resultSet.getString("confirmation_code"));
            booking.setUserId(resultSet.getInt("user_id"));
            booking.setAgentId(resultSet.getInt("agent_id"));
            bookings.add(booking);
        }

        return bookings;
    }
    
}
