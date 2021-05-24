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
public class AdminTravelerMenu extends BaseAdminMenu {

    public AdminTravelerMenu(AdminService adminService) {
        super(adminService);
    }

    @Override
    public void run() {
        OptionsMenu.create()
                .addOption("Add", this::addTraveler)
                .addOption("Update", this::updateTraveler)
                .addOption("Delete", this::deleteTraveler)
                .addOption("Read all", this::readAllTravelers).run();
    }

    private void addTraveler() {
        User traveler = new User();
        traveler.setRoleId(UserRole.TRAVELER.getValue());

        traveler.setGivenName(StringMenu.create().setTitle("Enter your first name").run());
        traveler.setFamilyName(StringMenu.create().setTitle("Enter your last name").run());
        traveler.setUsername(StringMenu.create().setTitle("Enter a username").run());
        traveler.setPassword(StringMenu.create().setTitle("Enter a password").run());
        traveler.setEmail(StringMenu.create().setTitle("Enter your email").run());
        traveler.setPhone(StringMenu.create().setTitle("Enter your phone number").run());

        adminService.addUser(traveler);
    }

    private void updateTraveler() {
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

        traveler.setGivenName(StringMenu.create().setTitle("Enter your first name, or N/A to for " + traveler.getGivenName()).setNoneValue(traveler.getGivenName()).run());
        traveler.setFamilyName(StringMenu.create().setTitle("Enter your last name, or N/A to for " + traveler.getFamilyName()).setNoneValue(traveler.getFamilyName()).run());
        traveler.setUsername(StringMenu.create().setTitle("Enter a username, or N/A to for " + traveler.getUsername()).setNoneValue(traveler.getUsername()).run());
        traveler.setPassword(StringMenu.create().setTitle("Enter a password, or N/A to for " + traveler.getPassword()).setNoneValue(traveler.getPassword()).run());
        traveler.setEmail(StringMenu.create().setTitle("Enter your email, or N/A to for " + traveler.getEmail()).setNoneValue(traveler.getEmail()).run());
        traveler.setPhone(StringMenu.create().setTitle("Enter your phone number, or N/A to for " + traveler.getPhone()).setNoneValue(traveler.getPhone()).run());

        adminService.updateUser(traveler);
    }

    private void deleteTraveler() {
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

        adminService.deleteUser(traveler);
    }

    private void readAllTravelers() {
        List<User> travelers = adminService.getAllUsers(UserRole.TRAVELER.getValue());
        for (User traveler : travelers) {
            System.out.println("  " + traveler.toString());
        }
    }

}
