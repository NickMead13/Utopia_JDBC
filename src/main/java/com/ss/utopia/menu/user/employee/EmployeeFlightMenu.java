package com.ss.utopia.menu.user.employee;

import com.ss.utopia.database.dao.RouteDAO;
import com.ss.utopia.database.entity.Airport;
import com.ss.utopia.database.entity.Flight;
import com.ss.utopia.database.entity.Route;
import com.ss.utopia.menu.*;
import com.ss.utopia.service.EmployeeService;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public class EmployeeFlightMenu extends BaseEmployeeMenu {

    public EmployeeFlightMenu(EmployeeService employeeService) {
        super(employeeService);
    }

    @Override
    public void run() {
        OptionsMenu flightMenu = OptionsMenu.create()
                .setTitle("Select a Flight to Manage");

        List<Flight> flights = employeeService.getAllFlights();
        List<Route> routes = employeeService.getAllRoutes();
        Map<Integer, Route> routeMap = new HashMap<>();
        for (Route route : routes) {
            routeMap.put(route.getId(), route);
        }

        for (Flight flight : flights) {
            flightMenu.addOption(routeMap.get(flight.getRouteId()).toString());
        }
        flightMenu.addQuit(null);
        int result = flightMenu.run();
        if (result >= flights.size()) return;

        Flight flight = flights.get(result);
        OptionsMenu.create()
                .setTitle("Select an Action")
                .addOption("View more details about the flight", () -> runViewFlightDetails(flight, routeMap.get(flight.getRouteId())))
                .addOption("Update the details of the flight", () -> runUpdateFlight(flight, routeMap.get(flight.getRouteId())))
                .addOption("Add Seats to Flight", () -> runAddSeats(flight))
                .addQuit(null).run();
    }

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss z");

    private void runViewFlightDetails(Flight flight, Route route) {
        System.out.println("You have chosen to view the Flight with Flight Id: " + flight.getId() +
                " and Departure Airport: " + route.getOrigin().getAirportCode() +
                " and Arrival Airport: " + route.getDestination().getAirportCode());

        System.out.println("Departure Airport: " + route.getOrigin().toString());
        System.out.println("Arrival Airport: " + route.getDestination().toString());

        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(flight.getDepartureTime().getTime()), ZoneId.systemDefault());
        System.out.println("Departure Date: " + zdt.format(dateFormatter));
        System.out.println("Departure Time: " + zdt.format(timeFormatter));
    }

    private void runUpdateFlight(Flight flight, Route route) {
        System.out.println("Available Airports to choose from");
        for (Airport airport : employeeService.getAllAirports()) {
            System.out.println("  " + airport.toString());
        }
        while (true) {
            String origin = StringMenu.create()
                    .setTitle("Enter a new Departure Airport, or N/A for no change (currently " + route.getOrigin().getAirportCode() + ")")
                    .setNoneValue(route.getOrigin().getAirportCode())
                    .run();
            String destination = StringMenu.create()
                    .setTitle("Enter a new Arrival Airport, or N/A for no change (currently " + route.getDestination().getAirportCode() + ")")
                    .setNoneValue(route.getDestination().getAirportCode())
                    .run();

            if (origin.equalsIgnoreCase("N/A") || origin.length() != 3) {
                origin = route.getOrigin().getAirportCode();
            }
            if (destination.equalsIgnoreCase("N/A") || destination.length() != 3) {
                destination = route.getDestination().getAirportCode();
            }

            Route newRoute = employeeService.getRoute(origin, destination);
            if (newRoute == null) {
                System.err.println("No route like that currently exists!");
            } else {
                break;
            }
        }

        LocalDate departDate = DateMenu.create()
                .setTitle("Enter a new Departure Date").run();
        LocalTime departTime = TimeMenu.create()
                .setTitle("Enter a new Departure Time").run();
        //LocalDate arriveDate = DateMenu.create().setTitle("Enter a new Arrival Date, or N/A for no change").run();
        //LocalTime arriveTime = TimeMenu.create().setTitle("Enter a new Arrival Time, or N/A for no change").run();

        Timestamp departTimestamp = new Timestamp(ZonedDateTime.of(departDate, departTime, ZoneId.systemDefault()).toInstant().toEpochMilli());
        flight.setDepartureTime(departTimestamp);

        employeeService.updateFlight(flight);
    }

    private void runAddSeats(Flight flight) {
        flight.setReservedSeats(IntegerMenu.create()
                .setTitle("Enter a new reserved seats count, or N/A for no change (currently " + flight.getReservedSeats() + ")")
                .setNoneValue(flight.getReservedSeats()).run());

        flight.setSeatPrice(FloatMenu.create()
                .setTitle("Enter a new reserved seats count, or N/A for no change (currently " + flight.getSeatPrice() + ")")
                .setNoneValue(flight.getSeatPrice()).run());
    }

}
