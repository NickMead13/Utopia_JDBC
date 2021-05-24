package com.ss.utopia.menu;

/**
 * @author NickM13
 * @since 5/22/2021
 */
public class IntegerMenu extends BaseMenu<Integer> {

    public static IntegerMenu create() {
        return new IntegerMenu();
    }

    private float min = 0, max = 10000;
    private Integer noneValue = null;

    private IntegerMenu() {

    }

    /**
     * Set lower and upper boundaries (inclusive) of input value
     * @param min
     * @param max
     * @return self
     */
    public IntegerMenu setBoundaries(int min, int max) {
        this.min = min;
        this.max = max;
        return this;
    }

    /**
     * Allow a user to enter n/a to use a default value
     *
     * @param noneValue default value
     * @return self
     */
    public IntegerMenu setNoneValue(Integer noneValue) {
        this.noneValue = noneValue;
        return this;
    }

    /**
     * Set the display message when the menu is shown
     * @param title initial menu text
     * @return self
     */
    public IntegerMenu setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public Integer run() {
        while (true) {
            if (title != null) {
                System.out.println(title);
            }
            try {
                String line = scanner.nextLine();
                if (line.replaceAll("/", "").equalsIgnoreCase("n/a") && noneValue != null) {
                    return noneValue;
                }
                int input = Integer.parseInt(line);
                if (input < min || input > max) {
                    System.out.println("Expected an integer between " + min + " and " + max + "!");
                    continue;
                }
                return input;
            } catch (NumberFormatException exception) {
                System.out.println("Expected an integer between " + min + " and " + max + "!");
            }
        }
    }

}
