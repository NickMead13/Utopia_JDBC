package com.ss.utopia.database.entity;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class BookingPayment {

    private Integer bookingId;
    private String stripeId;
    private Boolean refunded;

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getStripeId() {
        return stripeId;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

    public Boolean getRefunded() {
        return refunded;
    }

    public void setRefunded(Boolean refunded) {
        this.refunded = refunded;
    }

}
