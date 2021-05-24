package com.ss.utopia.database.entity;

import java.sql.Timestamp;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public class FlightStatus {

    private Integer id;
    private Integer routeId;
    private Integer airplaneId;
    private Timestamp departureTime;
    private Integer reservedSeats;
    private Float seatPrice;
    private Long passengerCount;
    private Long availableSeats;

    public FlightStatus() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Integer airplaneId) {
        this.airplaneId = airplaneId;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(Integer reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public Float getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(Float seatPrice) {
        this.seatPrice = seatPrice;
    }

    public Long getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Long passengerCount) {
        this.passengerCount = passengerCount;
    }

    public Long getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Long availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "FlightStatus{" +
                "id=" + id +
                ", routeId=" + routeId +
                ", airplaneId=" + airplaneId +
                ", departureTime=" + departureTime +
                ", reservedSeats=" + reservedSeats +
                ", seatPrice=" + seatPrice +
                ", passengerCount=" + passengerCount +
                ", availableSeats=" + availableSeats +
                '}';
    }

}
