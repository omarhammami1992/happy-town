package com.happytown.infrastructure.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import com.happytown.domain.entity.Inhabitant;

@Entity
@Table(name = "Inhabitant")
public class InhabitantJpa {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDay;
    private LocalDate arrivalDate;
    private String address;
    private String gift;

    public InhabitantJpa() {
    }

    public InhabitantJpa(String id, String firstName, String lastName, String email, LocalDate birthDay, LocalDate arrivalDate, String address, String gift) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDay = birthDay;
        this.arrivalDate = arrivalDate;
        this.address = address;
        this.gift = gift;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public Inhabitant toDomain() {
        return new Inhabitant(id, email, birthDay, arrivalDate, gift);
    }
}
