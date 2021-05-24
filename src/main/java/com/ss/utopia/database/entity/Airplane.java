package com.ss.utopia.database.entity;

/**
 * @author NickM13
 * @since 5/21/2021
 */
public class Airplane {

    private Integer id;
    private AirplaneType type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AirplaneType getType() {
        return type;
    }

    public void setType(AirplaneType type) {
        this.type = type;
    }

}
