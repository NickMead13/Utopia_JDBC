package com.ss.utopia.database.dao;

import com.ss.utopia.database.entity.BookingPayment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public class BookingPaymentDAO extends BaseDAO<BookingPayment> {

    public BookingPaymentDAO(Connection connection) {
        super(connection);
    }

    public void addPayment(BookingPayment payment) {
        update("INSERT INTO booking_payment " +
                        "(booking_id, stripe_id, refunded) values (?, ?, ?)",
                new Object[] {payment.getBookingId(), payment.getStripeId(), payment.getRefunded()});
    }

    public void refundPayment(int bookingId) {
        update("UPDATE booking_payment " +
                "SET refunded = true " +
                "WHERE booking_id = ?", new Object[] {bookingId});
    }

    @Override
    public List<BookingPayment> parseData(ResultSet resultSet) throws SQLException {
        List<BookingPayment> payments = new ArrayList<>();

        while (resultSet.next()) {
            BookingPayment payment = new BookingPayment();
            payment.setBookingId(resultSet.getInt("booking_id"));
            payment.setStripeId(resultSet.getString("stripe_id"));
            payment.setRefunded(resultSet.getBoolean("refunded"));
            payments.add(payment);
        }

        return payments;
    }

}
