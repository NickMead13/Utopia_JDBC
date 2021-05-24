package com.ss.utopia.menu.user.traveler;

import com.ss.utopia.database.entity.*;
import com.ss.utopia.menu.OptionsMenu;
import com.ss.utopia.menu.StringMenu;
import com.ss.utopia.service.TravelerService;

import java.sql.Date;
import java.time.Instant;
import java.util.*;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public class TravelerBookTicketMenu extends BaseTravelerMenu {

    public TravelerBookTicketMenu(TravelerService travelerService, User traveler) {
        super(travelerService, traveler);
    }

    @Override
    public void run() {
        OptionsMenu flightMenu = OptionsMenu.create()
                .setTitle("Select a flight you want to book a ticket for");

        List<Route> routes = travelerService.getAllRoutes();
        Map<Integer, Route> routeMap = new HashMap<>();
        for (Route route : routes) {
            routeMap.put(route.getId(), route);
        }

        List<FlightStatus> flightStats = travelerService.getAllFlightStatuses();
        Map<Integer, FlightStatus> flightStatusMap = new HashMap<>();
        for (FlightStatus status : flightStats) {
            flightStatusMap.put(status.getId(), status);
        }

        List<Flight> flights = travelerService.getAllFlights();
        Iterator<Flight> fsit = flights.iterator();
        java.util.Date now = Date.from(Instant.now());
        while (fsit.hasNext()) {
            Flight flight = fsit.next();
            if (flight.getDepartureTime().before(now) ||
                    (flightStatusMap.containsKey(flight.getId()) &&
                    flightStatusMap.get(flight.getId()).getAvailableSeats() <= 0)) {
                fsit.remove();
            } else {
                flightMenu.addOption(routeMap.get(flight.getRouteId()).toString());
            }
        }
        flightMenu.addQuit(null);
        int result = flightMenu.run();
        if (result >= flights.size()) {
            return;
        }
        Flight flight = flights.get(result);

        // TODO: I'm not sure what a stripe id is
        String stripeId = StringMenu.create().setTitle("Enter a Stripe ID").run();

        travelerService.addBooking(flight, traveler, stripeId);
    }

}
