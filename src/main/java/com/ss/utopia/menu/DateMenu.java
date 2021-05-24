package com.ss.utopia.menu;

import java.time.LocalDate;

/**
 * @author NickM13
 * @since 5/22/2021
 */
public class DateMenu extends BaseMenu<LocalDate> {

    protected final static int ITEMS_PER_PAGE = 7;

    public static DateMenu create() {
        return new DateMenu();
    }

    private LocalDate noneValue = null;

    private DateMenu() {

    }

    public DateMenu setTitle(String title) {
        this.title = title;
        return this;
    }

    public DateMenu setNoneValue(LocalDate noneValue) {
        this.noneValue = noneValue;
        return this;
    }

    public LocalDate run() {
        while (true) {
            try {
                printMenu();
                String line = scanner.nextLine();
                if (line.replaceAll("/", "").equalsIgnoreCase("na") && noneValue != null) {
                    return noneValue;
                }
                int input = Integer.parseInt(line);
                if (input <= 0 || input > ITEMS_PER_PAGE + 2) {
                    System.out.println("Unexpected input");
                    continue;
                }
                if (input <= ITEMS_PER_PAGE) {
                    return LocalDate.now().plusDays((long) page * ITEMS_PER_PAGE + input);
                }
                if (input == ITEMS_PER_PAGE + 1) {
                    page++;
                } else if (page > 0) {
                    page--;
                } else {
                    System.out.println("Unexpected input");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Unexpected input");
            }
        }
    }

    private void printMenu() {
        System.out.println(title + " (page " + (page + 1) + ")");
        LocalDate localDate = LocalDate.now().plusDays((long) page * ITEMS_PER_PAGE + 1);
        for (int i = 0; i < ITEMS_PER_PAGE; i++) {
            System.out.println("  " + (i + 1) + ") " + localDate.plusDays(i));
        }
        System.out.println("  " + (ITEMS_PER_PAGE + 1) + ") Next Page");
        if (page > 0) {
            System.out.println("  " + (ITEMS_PER_PAGE + 2) + ") Previous Page");
        }
    }

}
