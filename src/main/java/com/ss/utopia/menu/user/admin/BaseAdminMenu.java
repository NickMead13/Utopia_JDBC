package com.ss.utopia.menu.user.admin;

import com.ss.utopia.service.AdminService;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public abstract class BaseAdminMenu {

    protected final AdminService adminService;

    public BaseAdminMenu(AdminService adminService) {
        this.adminService = adminService;
    }

    public abstract void run();

}
