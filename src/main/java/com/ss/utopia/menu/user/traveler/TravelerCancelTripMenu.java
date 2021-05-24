package com.ss.utopia.menu.user.traveler;

import com.ss.utopia.database.entity.*;
import com.ss.utopia.menu.OptionsMenu;
import com.ss.utopia.service.TravelerService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public class TravelerCancelTripMenu extends BaseTravelerMenu {

    public TravelerCancelTripMenu(TravelerService travelerService, User traveler) {
        super(travelerService, traveler);
    }

    @Override
    public void run() {
        OptionsMenu tripMenu = OptionsMenu.create()
                .setTitle("Select a ticket to cancel");

        // TODO: Redo these using SQL if have time
        List<Flight> flights = travelerService.getAllFlights();
        Map<Integer, Flight> flightMap = flights.stream().collect(Collectors.toMap(Flight::getId, i -> i));

        List<Route> routes = travelerService.getAllRoutes();
        Map<Integer, Route> routeMap = routes.stream().collect(Collectors.toMap(Route::getId, i -> i));

        List<FlightBookings> bookings = travelerService.getAllFlightBookings();
        Map<Integer, Flight> bookingToFlightMap = bookings.stream().collect(Collectors.toMap(FlightBookings::getBookingId, i -> flightMap.get(i.getFlightId())));

        List<UserBooking> userBookings = travelerService.getAllUserBookings(traveler);
        Iterator<UserBooking> ubit = userBookings.iterator();
        while (ubit.hasNext()) {
            UserBooking userBooking = ubit.next();
            if (!userBooking.getActive()) {
                ubit.remove();
                continue;
            }

            Flight flight = bookingToFlightMap.get(userBooking.getId());
            tripMenu.addOption(routeMap.get(flight.getRouteId()).toString());
        }
        tripMenu.addQuit(null);

        int result = tripMenu.run();
        if (result >= userBookings.size()) {
            return;
        }

        UserBooking userBooking = userBookings.get(result);

        travelerService.cancelTrip(userBooking.getId());
    }

}
