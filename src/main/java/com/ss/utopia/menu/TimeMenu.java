package com.ss.utopia.menu;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author NickM13
 * @since 5/22/2021
 */
public class TimeMenu extends BaseMenu<LocalTime> {

    private static final DateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public static TimeMenu create() {
        return new TimeMenu();
    }

    private LocalTime noneValue = null;

    private TimeMenu() {

    }

    public TimeMenu setTitle(String title) {
        this.title = title;
        return this;
    }

    public TimeMenu setNoneValue(LocalTime noneValue) {
        this.noneValue = noneValue;
        return this;
    }

    /**
     * Runs until a valid time is entered
     * @return
     */
    public LocalTime run() {
        while (true) {
            if (title != null) {
                System.out.println(title);
            }
            System.out.println("Time format: 'HH:mm'");
            String input = scanner.nextLine();
            if (input.replaceAll("/", "").equalsIgnoreCase("na") && noneValue != null) {
                return noneValue;
            }
            try {
                Date formatted = timeFormat.parse(input);
                return LocalTime.of(formatted.getHours(), formatted.getMinutes());
            } catch (ParseException e) {
                System.out.println("Unexpected input");
            }
        }
    }

}
