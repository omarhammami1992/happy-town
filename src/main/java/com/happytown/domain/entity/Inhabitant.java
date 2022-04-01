package com.happytown.domain.entity;

import java.time.LocalDate;

public class Inhabitant {
    private final String id;
    private final String email;
    private final LocalDate birthDay;
    private final LocalDate arrivalDate;
    private String gift;

    public Inhabitant(String id, String email, LocalDate birthDay, LocalDate arrivalDate, String gift) {
        this.id = id;
        this.email = email;
        this.birthDay = birthDay;
        this.arrivalDate = arrivalDate;
        this.gift = gift;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public String getGift() {
        return gift;
    }

    public void attributeGift(String gift) {
        this.gift = gift;
    }
}
