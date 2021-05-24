package com.ss.utopia.menu.user;

import com.ss.utopia.Utopia;
import com.ss.utopia.database.entity.User;
import com.ss.utopia.menu.OptionsMenu;
import com.ss.utopia.menu.StringMenu;
import com.ss.utopia.menu.user.traveler.TravelerBookTicketMenu;
import com.ss.utopia.menu.user.traveler.TravelerCancelTripMenu;
import com.ss.utopia.service.TravelerService;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class TravelerMainMenu {

    public static void start(Utopia utopia) {
        new TravelerMainMenu(utopia).login();
    }

    private final Utopia utopia;
    private final TravelerService travelerService = new TravelerService();
    private User traveler;

    private TravelerMainMenu(Utopia utopia) {
        this.utopia = utopia;
    }

    /**
     * Attempt to log the user into an traveler account, continuing to main menu on success or returning on failure
     */
    private void login() {
        int attempts = 1;
        while (true) {
            String username = StringMenu.create().setTitle("Enter your username").run();
            String password = StringMenu.create().setTitle("Enter your password").run();
            traveler = travelerService.getUserByLogin(username, password);
            if (traveler != null && traveler.getRoleId() == UserRole.TRAVELER.getValue() && traveler.getPassword().equals(password)) {
                System.out.println("Welcome, " + traveler.getGivenName() + "!");
                break;
            }
            System.out.println("Incorrect username or password, " + --attempts + " attempts remaining");
            if (attempts <= 0) {
                utopia.runMenuMain();
                return;
            }
        }
        runMainMenu();
    }

    /**
     * Main traveler menu
     */
    private void runMainMenu() {
        OptionsMenu.create()
                .setTitle("Traveler Menu")
                .addOption("Book a Ticket", this::runBookTicket)
                .addOption("Cancel an Upcoming Trip", this::runCancelTicket)
                .addQuit(utopia::runMenuMain)
                .run();
    }

    private void runBookTicket() {
        new TravelerBookTicketMenu(travelerService, traveler).run();
        runMainMenu();
    }

    private void runCancelTicket() {
        new TravelerCancelTripMenu(travelerService, traveler).run();
        runMainMenu();
    }

}
