package com.ss.utopia.menu;

/**
 * @author NickM13
 * @since 5/22/2021
 */
public class StringMenu extends BaseMenu<String> {

    public static StringMenu create() {
        return new StringMenu();
    }

    private int min = 0, max = 255;
    private String noneValue = null;

    private StringMenu() {

    }

    public StringMenu setNoneValue(String noneValue) {
        this.noneValue = noneValue;
        return this;
    }

    /**
     * Set lower and upper boundaries (inclusive) of input value
     * @param min
     * @param max
     * @return
     */
    public StringMenu setBoundaries(int min, int max) {
        this.min = min;
        this.max = max;
        return this;
    }

    /**
     * Set the display message when the menu is shown
     * @param title
     * @return
     */
    public StringMenu setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String run() {
        while (true) {
            if (title != null) {
                System.out.println(title);
            }
            String input = scanner.nextLine();
            if (input.replaceAll("/", "").equalsIgnoreCase("na") && noneValue != null) {
                return noneValue;
            }
            if (input.length() < min || input.length() > max) {
                System.out.println("Expected a string of size " + (min == max ? min : (min + " and " + max)) + "!");
                continue;
            }
            return input;
        }
    }

}
