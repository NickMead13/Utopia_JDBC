package com.ss.utopia.menu.user;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public enum UserRole {
    EMPLOYEE(1),
    TRAVELER(2);

    private final int value;

    UserRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
