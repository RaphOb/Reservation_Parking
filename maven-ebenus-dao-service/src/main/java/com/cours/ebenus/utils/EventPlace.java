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

        EventPlace that = (EventPlace) o;

        if (parkingRoom != that.parkingRoom) return false;
        if (!userEmail.equals(that.userEmail)) return false;
        if (!dateBook.equals(that.dateBook)) return false;
        return idEvent.equals(that.idEvent);
    }

    @Override
    public int hashCode() {
        int result = userEmail.hashCode();
        result = 31 * result + parkingRoom;
        result = 31 * result + dateBook.hashCode();
        result = 31 * result + idEvent.hashCode();
        return result;
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
