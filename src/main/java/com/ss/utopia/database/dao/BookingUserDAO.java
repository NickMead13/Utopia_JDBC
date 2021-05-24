package com.ss.utopia.database.dao;

import com.ss.utopia.database.entity.Airplane;
import com.ss.utopia.database.entity.AirplaneType;
import com.ss.utopia.database.entity.Booking;
import com.ss.utopia.database.entity.BookingUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class BookingUserDAO extends BaseDAO<Airplane> {

    public BookingUserDAO(Connection connection) {
        super(connection);
    }

    public void addUser(BookingUser bookingUser) {
        update("INSERT INTO booking_user " +
                        "(booking_id, user_id) values (?, ?)",
                new Object[] {bookingUser.getBookingId(), bookingUser.getUserId()});
    }

    @Override
    public List<Airplane> parseData(ResultSet resultSet) throws SQLException {
        List<Airplane> airplanes = new ArrayList<>();

        while (resultSet.next()) {
            Airplane airplane = new Airplane();
            airplane.setId(resultSet.getInt("id"));
            AirplaneType type = new AirplaneType();
            type.setId(resultSet.getInt("type_id"));
            type.setMaxCapacity(resultSet.getInt("max_capacity"));
            airplane.setType(type);
            airplanes.add(airplane);
        }

        return airplanes;
    }

}
