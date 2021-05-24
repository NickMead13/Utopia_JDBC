package com.ss.utopia.menu.user.admin;

import com.ss.utopia.database.entity.Airport;
import com.ss.utopia.database.entity.Route;
import com.ss.utopia.menu.OptionsMenu;
import com.ss.utopia.service.AdminService;

import java.util.List;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public class AdminRouteMenu extends BaseAdminMenu {

    public AdminRouteMenu(AdminService adminService) {
        super(adminService);
    }

    @Override
    public void run() {
        OptionsMenu.create()
                .setTitle("Manage Routes")
                .addOption("Add", () -> {
                    Route route = new Route();

                    OptionsMenu airportMenu = OptionsMenu.create()
                            .setTitle("Select an Origin Airport");
                    List<Airport> airports = adminService.getAllAirports();
                    for (Airport airport : airports) {
                        airportMenu.addOption(airport.toString());
                    }
                    Airport origin = airports.get(airportMenu.run());

                    airportMenu = OptionsMenu.create()
                            .setTitle("Select a Destination Airport");
                    airports = adminService.getAllAirportsNotOriginatedFrom(origin.getAirportCode());
                    for (Airport airport : airports) {
                        airportMenu.addOption(airport.toString());
                    }
                    Airport destination = airports.get(airportMenu.run());

                    route.setOrigin(origin);
                    route.setDestination(destination);

                    adminService.addRoute(route);
                })
                .addOption("Update", () -> {

                })
                .addOption("Delete", () -> {
                    List<Route> routes = adminService.getAllRoutes();
                    OptionsMenu routeMenu = OptionsMenu.create()
                            .setTitle("Delete a Route");
                    for (Route route : routes) {
                        routeMenu.addOption(route.toString());
                    }
                    routeMenu.addQuit(null);

                    int result = routeMenu.run();
                    if (result >= routes.size()) return;

                    adminService.deleteRoute(routes.get(result));
                })
                .addOption("Read all", () -> {
                    List<Route> routes = adminService.getAllRoutes();
                    for (Route route : routes) {
                        System.out.println("  " + route.toString());
                    }
                }).addQuit(null).run();
    }

}
