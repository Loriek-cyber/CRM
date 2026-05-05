package com.loriek.crmloriek.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UniqueIdentificator {
    @Id
    private String uuid;
    private String type;
    private String device;
    @ManyToOne
    private User user;
    public UniqueIdentificator(String uuid, User user) {
        this.uuid = uuid;
        this.user = user;
    }
    public UniqueIdentificator() {
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
