package com.ss.utopia.database.entity;

import java.sql.Timestamp;
import java.util.Random;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class Flight {

    private Integer id = null;
    // TODO: Every time routeId is used, it is to index the actual route object, is it safe to just read a route here?
    private Integer routeId;
    private Integer airplaneId;
    private Timestamp departureTime;
    private Integer reservedSeats;
    private Float seatPrice;

    public Integer getId() {
        if (id == null) {
            id = Math.abs(new Random().nextInt());
            System.out.println("Assigning random id to flight: " + id);
        }
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

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", routeId=" + routeId +
                ", airplaneId=" + airplaneId +
                ", departureTime=" + departureTime +
                ", reservedSeats=" + reservedSeats +
                ", seatPrice=" + seatPrice +
                '}';
    }

}
