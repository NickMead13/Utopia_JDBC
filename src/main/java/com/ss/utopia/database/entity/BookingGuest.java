package com.ss.utopia.database.entity;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class BookingGuest {

    private Integer bookingId;
    private String contactEmail;
    private String contactPhone;

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

}
