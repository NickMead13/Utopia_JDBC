package com.ss.utopia.menu.user.employee;

import com.ss.utopia.service.EmployeeService;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public abstract class BaseEmployeeMenu {

    protected final EmployeeService employeeService;

    public BaseEmployeeMenu(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public abstract void run();

}
