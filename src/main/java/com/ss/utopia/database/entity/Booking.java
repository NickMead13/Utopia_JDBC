package com.ss.utopia.database.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class Booking {

    private Integer id;
    private Boolean active;
    private String confirmationCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    private static final int CONF_CODE_LEN = 255;

    private static final List<Character> RANDOM_CHARS = new ArrayList<>();
    static {
        for (char i = '0'; i <= '9'; i++) {
            RANDOM_CHARS.add(i);
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            RANDOM_CHARS.add(i);
        }
        for (char i = 'a'; i <= 'z'; i++) {
            RANDOM_CHARS.add(i);
        }
    }

    public void generateConfirmationCode() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < CONF_CODE_LEN; i++) {
            builder.append(RANDOM_CHARS.get(random.nextInt(RANDOM_CHARS.size())));
        }
        this.confirmationCode = builder.toString();
    }

}
