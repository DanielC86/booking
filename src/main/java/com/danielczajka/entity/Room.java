package com.danielczajka.entity;

import javax.persistence.*;

@Entity
@Table(name="ROOM")
public class Room {

    @Id
    @Column(name="ROOM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="NAME")
    private String name;
    @Column(name="ROOM_NUMBER")
    private String number;
    @Column(name="SEAT_INFO")
    private String seatInfo;
    @Column(name="LOCATION")
    private String location;
    @Column(name="PROJECTOR")
    private String projector;
    @Column(name="PHONE_NUMBER")
    private String phoneNumber;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String isProjector() {
        return projector;
    }

    public void setProjector(String projector) {
        this.projector = projector;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
