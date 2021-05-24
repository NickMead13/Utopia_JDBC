package com.ss.utopia.database.entity;

import java.sql.Timestamp;

/**
 * @author NickM13
 * @since 5/23/2021
 */
public class UserBooking {

    private Integer id;
    private Boolean active;
    private String confirmationCode;
    private Integer userId;
    private Integer agentId;

    public UserBooking() {

    }

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

}
