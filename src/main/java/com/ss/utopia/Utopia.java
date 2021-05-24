package com.ss.utopia;

import com.ss.utopia.menu.user.AdminMainMenu;
import com.ss.utopia.menu.user.EmployeeMainMenu;
import com.ss.utopia.menu.user.TravelerMainMenu;
import com.ss.utopia.service.ConnectionUtil;
import com.ss.utopia.menu.OptionsMenu;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class Utopia {

    public static void main(String[] args) {
        Utopia utopia = new Utopia();
        utopia.runMenuMain();
    }

    ConnectionUtil connectionUtil = new ConnectionUtil();

    public Utopia() {

    }

    /**
     * Main menu called on application startup
     */
    public void runMenuMain() {
        OptionsMenu.create()
                .setTitle("Welcome to the Utopia Airlines Management System. Which category of a user are you?")
                .addOption("Employee", () -> EmployeeMainMenu.start(this))
                .addOption("Administrator", () -> AdminMainMenu.start(this))
                .addOption("Traveler", () -> TravelerMainMenu.start(this))
                .addOption("Exit")
                .run();
    }

}
