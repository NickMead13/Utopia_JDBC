package com.ss.utopia.menu.user.admin;

import com.ss.utopia.database.entity.Airport;
import com.ss.utopia.database.entity.Route;
import com.ss.utopia.menu.OptionsMenu;
import com.ss.utopia.menu.StringMenu;
import com.ss.utopia.service.AdminService;

import java.util.List;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public class AdminAirportMenu extends BaseAdminMenu {

    public AdminAirportMenu(AdminService adminService) {
        super(adminService);
    }

    public void run() {
        OptionsMenu.create()
                .setTitle("Manage Airports")
                .addOption("Add", () -> {
                    Airport airport = new Airport();

                    airport.setAirportCode(StringMenu.create().setTitle("Enter a 3 digit code").setBoundaries(3, 3).run());
                    airport.setCityName(StringMenu.create().setTitle("Enter the airport city").setBoundaries(1, 45).run());

                    adminService.addAirport(airport);
                })
                .addOption("Update", () -> {
                    OptionsMenu airportMenu = OptionsMenu.create()
                            .setTitle("Update an Airport");

                    List<Airport> airports = adminService.getAllAirports();
                    for (Airport airport : airports) {
                        airportMenu.addOption(airport.toString());
                    }
                    Airport airport = airports.get(airportMenu.run());

                    OptionsMenu.create()
                            .setTitle("Update Origin Airport " + airport.getAirportCode())
                            .addOption("Change City Name", () -> changeCityName(airport))
                            .addOption("Create Route", () -> createRoute(airport))
                            .addOption("Remove Route", () -> removeRoute(airport))
                            .addQuit(null).run();
                })
                .addOption("Delete", () -> {
                    OptionsMenu airportMenu = OptionsMenu.create()
                            .setTitle("Delete an Airport");

                    List<Airport> airports = adminService.getAllAirports();
                    for (Airport airport : airports) {
                        airportMenu.addOption(airport.toString());
                    }
                    Airport airport = airports.get(airportMenu.run());

                    adminService.deleteAirport(airport);
                })
                .addOption("Read all", () -> {
                    List<Airport> airports = adminService.getAllAirports();
                    for (Airport airport : airports) {
                        System.out.println("  " + airport.toString());
                    }
                }).addQuit(null).run();
    }

    private void changeCityName(Airport airport) {
        airport.setCityName(StringMenu.create().setTitle("Change city name of " + airport).setBoundaries(1, 45).run());
        adminService.updateAirport(airport);
    }

    private void createRoute(Airport origin) {
        OptionsMenu airportMenu = OptionsMenu.create()
                .setTitle("Remove connected route");

        List<Airport> airports = adminService.getAllAirportsNotOriginatedFrom(origin.getAirportCode());
        for (Airport airport : airports) {
            airportMenu.addOption(airport.toString());
        }
        airportMenu.addQuit(null);

        int result = airportMenu.run();
        if (result == airports.size()) return;

        Route route = new Route();
        route.setOrigin(origin);
        route.setDestination(airports.get(result));
        adminService.addRoute(route);
    }

    private void removeRoute(Airport origin) {
        OptionsMenu routeMenu = OptionsMenu.create()
                .setTitle("Remove connected route");

        List<Route> routes = adminService.getAllRoutesFromOrigin(origin.getAirportCode());
        for (Route route : routes) {
            routeMenu.addOption(route.toString());
        }
        routeMenu.addQuit(null);

        int result = routeMenu.run();
        if (result == routes.size()) return;
        Route route = routes.get(result);
    }

}
