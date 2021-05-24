package com.ss.utopia.menu.user.admin;

import com.ss.utopia.database.entity.*;
import com.ss.utopia.menu.OptionsMenu;
import com.ss.utopia.menu.user.UserRole;
import com.ss.utopia.service.AdminService;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public class AdminTripCancelMenu extends BaseAdminMenu {

    public AdminTripCancelMenu(AdminService adminService) {
        super(adminService);
    }

    @Override
    public void run() {
        OptionsMenu userMenu = OptionsMenu.create()
                .setTitle("Select an Traveler");

        List<User> travelers = adminService.getAllUsers(UserRole.TRAVELER.getValue());
        for (User traveler : travelers) {
            userMenu.addOption(traveler.toString());
        }
        userMenu.addQuit(null);
        int result = userMenu.run();
        if (result >= travelers.size()) return;

        User traveler = travelers.get(result);

        OptionsMenu tripMenu = OptionsMenu.create()
                .setTitle("Select a ticket to cancel");

        // TODO: Redo these using SQL if have time
        List<Flight> flights = adminService.getAllFlights();
        Map<Integer, Flight> flightMap = flights.stream().collect(Collectors.toMap(Flight::getId, i -> i));

        List<Route> routes = adminService.getAllRoutes();
        Map<Integer, Route> routeMap = routes.stream().collect(Collectors.toMap(Route::getId, i -> i));

        List<FlightBookings> bookings = adminService.getAllFlightBookings();
        Map<Integer, Flight> bookingToFlightMap = bookings.stream().collect(Collectors.toMap(FlightBookings::getBookingId, i -> flightMap.get(i.getFlightId())));

        List<UserBooking> userBookings = adminService.getAllUserBookings(traveler);
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

        result = tripMenu.run();
        if (result >= userBookings.size()) {
            return;
        }

        UserBooking userBooking = userBookings.get(result);

        adminService.cancelTrip(userBooking.getId());
    }

}
