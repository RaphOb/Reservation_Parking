package com.cours.ebenus.utils;

import com.google.api.client.util.DateTime;

public class EventPlace {
    private String userEmail;
    private int parkingRoom;
    private DateTime dateBook;
    private String idEvent;

    public String getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getParkingRoom() {
        return parkingRoom;
    }

    public void setParkingRoom(int parkingRoom) {
        this.parkingRoom = parkingRoom;
    }

    public DateTime getDateBook() {
        return dateBook;
    }

    public void setDateBook(DateTime dateBook) {
        this.dateBook = dateBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventPlace eventPlace = (EventPlace) o;

        return parkingRoom == eventPlace.parkingRoom;
    }

    @Override
    public int hashCode() {
        return parkingRoom;
    }

    @Override
    public String toString() {
        return "EventPlace{" +
                "userEmail='" + userEmail + '\'' +
                ", parkingRoom=" + parkingRoom +
                ", dateBook=" + dateBook +
                '}';
    }
}
