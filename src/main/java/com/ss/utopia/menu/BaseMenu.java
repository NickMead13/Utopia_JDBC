package com.ss.utopia.menu;

import java.util.Scanner;

/**
 * @author NickM13
 * @since 5/22/2021
 */
public abstract class BaseMenu<T> {

    protected static final Scanner scanner = new Scanner(System.in);

    protected String title = null;
    protected int page = 0;

    public abstract T run();

}
