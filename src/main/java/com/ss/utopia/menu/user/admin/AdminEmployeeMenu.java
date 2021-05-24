package com.ss.utopia.menu.user.admin;

import com.ss.utopia.database.entity.User;
import com.ss.utopia.menu.OptionsMenu;
import com.ss.utopia.menu.StringMenu;
import com.ss.utopia.menu.user.UserRole;
import com.ss.utopia.service.AdminService;

import java.util.List;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public class AdminEmployeeMenu extends BaseAdminMenu {

    public AdminEmployeeMenu(AdminService adminService) {
        super(adminService);
    }

    @Override
    public void run() {
        OptionsMenu.create()
                .addOption("Add", this::addEmployee)
                .addOption("Update", this::updateEmployee)
                .addOption("Delete", this::deleteEmployee)
                .addOption("Read all", this::readAllEmployees)
                .addQuit(null).run();
    }

    private void addEmployee() {
        User employee = new User();
        employee.setRoleId(UserRole.EMPLOYEE.getValue());

        employee.setGivenName(StringMenu.create().setTitle("Enter your first name").run());
        employee.setFamilyName(StringMenu.create().setTitle("Enter your last name").run());
        employee.setUsername(StringMenu.create().setTitle("Enter a username").run());
        employee.setPassword(StringMenu.create().setTitle("Enter a password").run());
        employee.setEmail(StringMenu.create().setTitle("Enter your email").run());
        employee.setPhone(StringMenu.create().setTitle("Enter your phone number").run());

        adminService.addUser(employee);
    }

    private void updateEmployee() {
        OptionsMenu userMenu = OptionsMenu.create()
                .setTitle("Select an Employee");

        List<User> employees = adminService.getAllUsers(UserRole.EMPLOYEE.getValue());
        for (User employee : employees) {
            userMenu.addOption(employee.toString());
        }
        userMenu.addQuit(null);
        int result = userMenu.run();
        if (result >= employees.size()) return;

        User employee = employees.get(result);

        employee.setGivenName(StringMenu.create().setTitle("Enter your first name, or N/A to for " + employee.getGivenName()).setNoneValue(employee.getGivenName()).run());
        employee.setFamilyName(StringMenu.create().setTitle("Enter your last name, or N/A to for " + employee.getFamilyName()).setNoneValue(employee.getFamilyName()).run());
        employee.setUsername(StringMenu.create().setTitle("Enter a username, or N/A to for " + employee.getUsername()).setNoneValue(employee.getUsername()).run());
        employee.setPassword(StringMenu.create().setTitle("Enter a password, or N/A to for " + employee.getPassword()).setNoneValue(employee.getPassword()).run());
        employee.setEmail(StringMenu.create().setTitle("Enter your email, or N/A to for " + employee.getEmail()).setNoneValue(employee.getEmail()).run());
        employee.setPhone(StringMenu.create().setTitle("Enter your phone number, or N/A to for " + employee.getPhone()).setNoneValue(employee.getPhone()).run());

        adminService.updateUser(employee);
    }

    private void deleteEmployee() {
        OptionsMenu userMenu = OptionsMenu.create()
                .setTitle("Select an Employee");

        List<User> employees = adminService.getAllUsers(UserRole.EMPLOYEE.getValue());
        for (User employee : employees) {
            userMenu.addOption(employee.toString());
        }
        userMenu.addQuit(null);
        int result = userMenu.run();
        if (result >= employees.size()) return;

        User employee = employees.get(result);

        adminService.deleteUser(employee);
    }

    private void readAllEmployees() {
        List<User> employees = adminService.getAllUsers(UserRole.EMPLOYEE.getValue());
        for (User employee : employees) {
            System.out.println("  " + employee.toString());
        }
    }

}
