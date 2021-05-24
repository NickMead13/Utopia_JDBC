package com.ss.utopia.menu.user;

import com.ss.utopia.Utopia;
import com.ss.utopia.menu.*;
import com.ss.utopia.menu.user.admin.*;
import com.ss.utopia.service.AdminService;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class AdminMainMenu {

    public static void start(Utopia utopia) {
        new AdminMainMenu(utopia).runMainMenu();
    }

    private final Utopia utopia;
    private final AdminService adminService = new AdminService();

    private AdminMainMenu(Utopia utopia) {
        this.utopia = utopia;
    }

    /**
     * Main administrator menu
     */
    private void runMainMenu() {
        OptionsMenu.create()
                .setTitle("Administrator Menu")
                .addOption("Manage Flights", this::runMenuManageFlights)
                // TODO: Not sure which db this one is for
                //.addOption("Manage Tickets", this::runMenuManageTickets)
                .addOption("Manage Airports", this::runMenuManageAirports)
                .addOption("Manage Routes", this::runMenuManageRoutes)
                .addOption("Manage Travelers", this::runMenuManageTravelers)
                .addOption("Manage Employees", this::runMenuManageEmployees)
                .addOption("Override Trip Cancellation", this::runMenuTripCancellation)
                .addQuit(utopia::runMenuMain)
                .run();
    }

    private void runMenuManageFlights() {
        new AdminFlightMenu(adminService).run();
        runMainMenu();
    }

    private void runMenuManageTickets() {
        new AdminTicketMenu(adminService).run();
        runMainMenu();
    }

    private void runMenuManageAirports() {
        new AdminAirportMenu(adminService).run();
        runMainMenu();
    }

    private void runMenuManageRoutes() {
        new AdminRouteMenu(adminService).run();
        runMainMenu();
    }

    private void runMenuManageTravelers() {
        new AdminTravelerMenu(adminService).run();
        runMainMenu();
    }

    private void runMenuManageEmployees() {
        new AdminEmployeeMenu(adminService).run();
        runMainMenu();
    }

    private void runMenuTripCancellation() {
        new AdminTripCancelMenu(adminService).run();
        runMainMenu();
    }

}
