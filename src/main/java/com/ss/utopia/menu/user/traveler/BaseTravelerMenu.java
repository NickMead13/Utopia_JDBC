package com.ss.utopia.menu.user.traveler;

import com.ss.utopia.database.entity.User;
import com.ss.utopia.service.TravelerService;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public abstract class BaseTravelerMenu {

    protected final TravelerService travelerService;
    protected final User traveler;

    public BaseTravelerMenu(TravelerService travelerService, User traveler) {
        this.travelerService = travelerService;
        this.traveler = traveler;
    }

    public abstract void run();

}
