package bai8_flight;

import java.io.Serializable;

public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;

    private String flightNumber;
    private String route;
    private int totalSeats;
    private int availableSeats;

    public Flight(String flightNumber, String route, int totalSeats) {
        this.flightNumber = flightNumber;
        this.route = route;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getRoute() {
        return route;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public synchronized int getAvailableSeats() {
        return availableSeats;
    }

    public synchronized boolean book(int seats) {
        if (seats <= 0 || seats > availableSeats) {
            return false;
        }
        availableSeats -= seats;
        return true;
    }

    public synchronized boolean cancel(int seats) {
        if (seats <= 0 || (availableSeats + seats > totalSeats)) {
            return false;
        }
        availableSeats += seats;
        return true;
    }

    @Override
    public String toString() {
        return String.format("[Chuyen bay: %-7s | Tuyen: %-25s | Con trong: %3d / %3d ghe]",
                flightNumber, route, availableSeats, totalSeats);
    }
}
