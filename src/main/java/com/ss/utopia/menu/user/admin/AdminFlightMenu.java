package com.ss.utopia.menu.user.admin;

import com.ss.utopia.database.entity.Airplane;
import com.ss.utopia.database.entity.Airport;
import com.ss.utopia.database.entity.Flight;
import com.ss.utopia.database.entity.Route;
import com.ss.utopia.menu.*;
import com.ss.utopia.service.AdminService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public class AdminFlightMenu extends BaseAdminMenu {

    public AdminFlightMenu(AdminService adminService) {
        super(adminService);
    }

    public void run() {
        OptionsMenu.create()
                .setTitle("Manage Flights")
                .addOption("Add", () -> {
                    Flight flight = new Flight();

                    setFlightValues(flight);

                    adminService.addFlight(flight);
                })
                .addOption("Update", () -> {
                    OptionsMenu flightMenu = OptionsMenu.create()
                            .setTitle("Update a Flight");

                    List<Flight> flights = adminService.getAllFlights();
                    for (Flight flight : flights) {
                        flightMenu.addOption(flight.getId().toString());
                    }
                    Flight flight = flights.get(flightMenu.run());

                    setFlightValues(flight);

                    adminService.updateFlight(flight);
                })
                .addOption("Delete", () -> {
                    OptionsMenu flightMenu = OptionsMenu.create()
                            .setTitle("Delete a Flight");

                    List<Flight> flights = adminService.getAllFlights();
                    for (Flight flight : flights) {
                        flightMenu.addOption(flight.getId().toString());
                    }
                    flightMenu.addQuit(null);

                    int result = flightMenu.run();
                    if (result >= flights.size()) return;

                    adminService.deleteFlight(flights.get(result));
                })
                .addOption("Read all", () -> {
                    List<Flight> flights = adminService.getAllFlights();
                    for (Flight flight : flights) {
                        System.out.println("  " + flight.toString());
                    }
                }).addQuit(null).run();
    }

    private void setFlightValues(Flight flight) {
        OptionsMenu menuAirports;
        menuAirports = OptionsMenu.create()
                .setTitle("Select a Origin/Departure Airport");

        List<Route> routes = adminService.getAllRoutes();
        List<Airport> airports = new ArrayList<>();
        Map<Airport, List<Route>> routeMap = new HashMap<>();
        for (Route route : routes) {
            if (!routeMap.containsKey(route.getOrigin())) {
                routeMap.put(route.getOrigin(), new ArrayList<>());
                airports.add(route.getOrigin());
                menuAirports.addOption(route.getOrigin().getAirportCode() + ", " + route.getOrigin().getCityName());
            }
            routeMap.get(route.getOrigin()).add(route);
        }

        Airport origin = airports.get(menuAirports.run());
        menuAirports = OptionsMenu.create()
                .setTitle("Select a Destination Airports");
        for (Route route : routeMap.get(origin)) {
            menuAirports.addOption(route.getDestination().getAirportCode() + ", " + route.getDestination().getCityName());
        }

        Route route = routeMap.get(origin).get(menuAirports.run());
        flight.setRouteId(route.getId());

        DateMenu menuDeparture = DateMenu.create()
                .setTitle("Select a Departure Date");

        LocalDate date = menuDeparture.run();
        LocalTime time = TimeMenu.create().setTitle("Set the Departure Time").run();

        Timestamp departureTime = new Timestamp(ZonedDateTime.of(date, time, ZoneId.systemDefault()).toInstant().toEpochMilli());

        flight.setDepartureTime(departureTime);

        List<Airplane> airplanes;
        int reservedSeats;
        while (true) {
            reservedSeats = IntegerMenu.create().setTitle("Reserved Seats").setBoundaries(1, 10000).run();
            airplanes = adminService.getAirplanesBySeatCount(reservedSeats);
            if (airplanes.isEmpty()) {
                System.out.println("There are no airplanes with that many seats!");
            } else {
                break;
            }
        }
        flight.setReservedSeats(reservedSeats);

        OptionsMenu airplaneMenu = OptionsMenu.create()
                .setTitle("Select an Airplane");
        for (Airplane airplane : airplanes) {
            airplaneMenu.addOption("Available Seating: " + airplane.getType().getMaxCapacity());
        }
        flight.setAirplaneId(airplanes.get(airplaneMenu.run()).getId());

        flight.setSeatPrice(FloatMenu.create().setTitle("Seat Price").setBoundaries(0, 10000).run());
    }

}
