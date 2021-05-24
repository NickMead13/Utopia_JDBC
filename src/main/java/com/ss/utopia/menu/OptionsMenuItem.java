package com.ss.utopia.menu;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class OptionsMenuItem {

    private final String description;
    private final MenuAction action;

    public OptionsMenuItem(String description, MenuAction action) {
        this.description = description;
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public MenuAction getAction() {
        return action;
    }

}
