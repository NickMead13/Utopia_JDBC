package com.ss.utopia.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class OptionsMenu extends BaseMenu<Integer> {

    /**
     * Returns a new instance of OptionsMenu
     * @return
     */
    public static OptionsMenu create() {
        return new OptionsMenu();
    }

    private final List<OptionsMenuItem> options = new ArrayList<>();

    private OptionsMenu() {

    }

    /**
     * Set the display message when the menu is shown
     * @param title
     * @return
     */
    public OptionsMenu setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Create and add a menu item to the options list without an action
     * @param description
     * @return
     */
    public OptionsMenu addOption(String description) {
        return addOption(description, null);
    }

    /**
     * Create and add a menu item to the options list with an action to be performed on selection
     * @param description
     * @param action
     * @return
     */
    public OptionsMenu addOption(String description, MenuAction action) {
        options.add(new OptionsMenuItem(description, action));
        return this;
    }

    /**
     * Shortcut function to create a "Quit to previous" button
     * @param action
     * @return
     */
    public OptionsMenu addQuit(MenuAction action) {
        addOption("Quit to previous", action);
        return this;
    }

    /**
     * Remove a menu item from the options list
     * @param index
     * @return
     */
    public OptionsMenuItem removeOption(int index) {
        return options.remove(index);
    }

    /**
     * Returns the total number of options
     * @return
     */
    public int getOptionCount() {
        return options.size();
    }

    /**
     * Prints all menu options and waits for user input to decide which option is run,
     * repeating until a valid option has been entered
     * @return Entered number minus 1 (starts from 0)
     */
    public Integer run() {
        while (true) {
            printMenu();
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input <= 0 || input > options.size()) {
                    System.out.println("Expected an integer between 1 and " + options.size() + "!");
                    continue;
                }
                MenuAction action = options.get(input - 1).getAction();
                if (action != null) action.call();
                return input - 1;
            } catch (NumberFormatException exception) {
                System.out.println("Expected an integer between 1 and " + options.size() + "!");
            }
        }
    }

    /**
     * Displays the title and list of options in console
     */
    private void printMenu() {
        if (title != null) {
            System.out.println(title);
        }
        for (int i = 0; i < options.size(); i++) {
            System.out.println("  " + (i + 1) + ") " + options.get(i).getDescription());
        }
    }

}
