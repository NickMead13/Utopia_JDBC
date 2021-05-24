package com.ss.utopia.menu.user;

import com.ss.utopia.Utopia;
import com.ss.utopia.database.entity.User;
import com.ss.utopia.menu.IntegerMenu;
import com.ss.utopia.menu.OptionsMenu;
import com.ss.utopia.menu.StringMenu;
import com.ss.utopia.menu.user.employee.EmployeeFlightMenu;
import com.ss.utopia.service.EmployeeService;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class EmployeeMainMenu {

    public static void start(Utopia utopia) {
        new EmployeeMainMenu(utopia).login();
    }

    private final Utopia utopia;
    private final EmployeeService employeeService = new EmployeeService();
    private User employee;

    private EmployeeMainMenu(Utopia utopia) {
        this.utopia = utopia;
    }

    private void login() {
        int attempts = 3;
        while (true) {
            String username = StringMenu.create().setTitle("Enter your username").run();
            String password = StringMenu.create().setTitle("Enter your password").run();
            User user = employeeService.getUserByLogin(username, password);
            if (user != null && user.getRoleId() == UserRole.EMPLOYEE.getValue() && user.getPassword().equals(password)) {
                System.out.println("Welcome, " + user.getGivenName() + "!");
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

    private void runMainMenu() {
        OptionsMenu.create()
                .setTitle("Employee Menu")
                .addOption("Enter Flights You Manage", this::runMenuManagedFlights)
                .addOption("Quit to previous", utopia::runMenuMain)
                .run();
    }

    private void runMenuManagedFlights() {
        new EmployeeFlightMenu(employeeService).run();
        runMainMenu();
    }

}
