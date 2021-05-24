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
        new EmployeeMainMenu(utopia).runMainMenu();
    }

    private final Utopia utopia;
    private final EmployeeService employeeService = new EmployeeService();
    private User employee;

    private EmployeeMainMenu(Utopia utopia) {
        this.utopia = utopia;
    }

    /**
     * Attempt to log the user into an employee account, continuing to main menu on success or returning on failure
     */
    private void login() {
        int attempts = 3;
        while (true) {
            String username = StringMenu.create().setTitle("Enter your username").run();
            String password = StringMenu.create().setTitle("Enter your password").run();
            employee = employeeService.getUserByLogin(username, password);
            if (employee != null && employee.getRoleId() == UserRole.EMPLOYEE.getValue() && employee.getPassword().equals(password)) {
                System.out.println("Welcome, " + employee.getGivenName() + "!");
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
     * Main employee menu
     */
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
