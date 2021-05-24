package com.ss.utopia.menu;

/**
 * @author NickM13
 * @since 5/22/2021
 */
public class FloatMenu extends BaseMenu<Float> {

    public static FloatMenu create() {
        return new FloatMenu();
    }

    private float min = 0, max = 10000;
    private Float noneValue = null;

    private FloatMenu() {

    }

    /**
     * Set lower and upper boundaries (inclusive) of input value
     * @param min
     * @param max
     * @return
     */
    public FloatMenu setBoundaries(float min, float max) {
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
    public FloatMenu setNoneValue(Float noneValue) {
        this.noneValue = noneValue;
        return this;
    }

    /**
     * Set the display message when the menu is shown
     * @param title
     * @return
     */
    public FloatMenu setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public Float run() {
        while (true) {
            if (title != null) {
                System.out.println(title);
            }
            try {
                String line = scanner.nextLine();
                if (line.replaceAll("/", "").equalsIgnoreCase("na") && noneValue != null) {
                    return noneValue;
                }
                float input = Float.parseFloat(line);
                if (input < min || input > max) {
                    System.out.println("Expected a float between " + min + " and " + max + "!");
                    continue;
                }
                return input;
            } catch (NumberFormatException exception) {
                System.out.println("Expected a float between " + min + " and " + max + "!");
            }
        }
    }

}
