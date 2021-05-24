package com.ss.utopia.database.entity;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class Route {

    private Integer id;
    private Airport origin = new Airport();
    private Airport destination = new Airport();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return origin.getAirportCode() + " -> " + destination.getAirportCode();
    }
}
