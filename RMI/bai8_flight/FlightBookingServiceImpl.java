package bai8_flight;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FlightBookingServiceImpl extends UnicastRemoteObject implements FlightBookingService {

    private final Map<String, Flight> flightDatabase = new ConcurrentHashMap<>();

    public FlightBookingServiceImpl() throws RemoteException {
        super();
        // Khoi tao cac chuyen bay mac dinh
        addFlight(new Flight("VN101", "Ha Noi -> TP Ho Chi Minh", 100));
        addFlight(new Flight("VJ202", "TP Ho Chi Minh -> Da Nang", 50));
        addFlight(new Flight("QH303", "Da Nang -> Ha Noi", 80));
        addFlight(new Flight("VN404", "TP Ho Chi Minh -> Phu Quoc", 30));
    }

    private void addFlight(Flight flight) {
        flightDatabase.put(flight.getFlightNumber().toUpperCase(), flight);
    }

    @Override
    public synchronized boolean bookTicket(String flightNumber, int seats) throws RemoteException {
        if (flightNumber == null) return false;
        Flight flight = flightDatabase.get(flightNumber.toUpperCase());
        if (flight == null) {
            System.out.printf("[Server] Dat ve that bai: Khong tim thay chuyen bay %s%n", flightNumber);
            return false;
        }

        boolean success = flight.book(seats);
        if (success) {
            System.out.printf("[Server] Dat ve thanh cong: Chuyen bay %s, So luong: %d ghe. Con lai: %d%n",
                    flightNumber, seats, flight.getAvailableSeats());
        } else {
            System.out.printf("[Server] Dat ve that bai: Chuyen bay %s khong du ghe (yeu cau %d, con %d)%n",
                    flightNumber, seats, flight.getAvailableSeats());
        }
        return success;
    }

    @Override
    public synchronized int availableSeats(String flightNumber) throws RemoteException {
        if (flightNumber == null) return -1;
        Flight flight = flightDatabase.get(flightNumber.toUpperCase());
        if (flight == null) {
            System.out.printf("[Server] Kiem tra ghe that bai: Khong tim thay chuyen bay %s%n", flightNumber);
            return -1; // -1 bieu thi khong ton tai chuyen bay
        }
        int available = flight.getAvailableSeats();
        System.out.printf("[Server] Chuyen bay %s hien con trong: %d ghe%n", flightNumber, available);
        return available;
    }

    @Override
    public synchronized boolean cancelBooking(String flightNumber, int seats) throws RemoteException {
        if (flightNumber == null) return false;
        Flight flight = flightDatabase.get(flightNumber.toUpperCase());
        if (flight == null) {
            System.out.printf("[Server] Huy ve that bai: Khong tim thay chuyen bay %s%n", flightNumber);
            return false;
        }

        boolean success = flight.cancel(seats);
        if (success) {
            System.out.printf("[Server] Huy ve thanh cong: Chuyen bay %s, So luong: %d ghe. Con lai: %d%n",
                    flightNumber, seats, flight.getAvailableSeats());
        } else {
            System.out.printf("[Server] Huy ve that bai: So luong huy vuot qua suc chua chuyen bay %s%n", flightNumber);
        }
        return success;
    }

    @Override
    public synchronized List<Flight> getAllFlights() throws RemoteException {
        return Collections.unmodifiableList(new ArrayList<>(flightDatabase.values()));
    }
}
