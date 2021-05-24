package com.ss.utopia.database.entity;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class BookingUser {

    private Integer bookingId;
    private Integer userId;

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
