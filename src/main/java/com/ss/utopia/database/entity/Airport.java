package com.ss.utopia.database.entity;

import java.util.Objects;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class Airport {

    private String airportCode;
    private String cityName;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return airportCode + ", " + cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return airportCode.equals(airport.airportCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airportCode);
    }

}
