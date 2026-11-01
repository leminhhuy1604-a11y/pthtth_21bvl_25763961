package bai8_flight;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class FlightBookingClient {
    public static final String HOST = "localhost";
    public static final int PORT = 1099;
    public static final String SERVICE_NAME = "FlightBookingService";

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            FlightBookingService service = (FlightBookingService) registry.lookup(SERVICE_NAME);
            System.out.println("[Client] Ket noi thanh cong toi FlightBookingService!");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n========== HE THONG DAT VE MAY BAY (RMI) ==========");
                System.out.println("1. Xem danh sach tat ca chuyen bay");
                System.out.println("2. Kiem tra so ghe trong (availableSeats)");
                System.out.println("3. Dat ve chuyen bay (bookTicket)");
                System.out.println("4. Huy ve da dat (cancelBooking)");
                System.out.println("0. Thoat");
                System.out.print("Lua chon cua ban: ");

                String choice = scanner.nextLine().trim();
                if (choice.equals("0")) {
                    System.out.println("[Client] Tam biet quy khach!");
                    break;
                }

                switch (choice) {
                    case "1":
                        List<Flight> flights = service.getAllFlights();
                        System.out.println("--- DANH SACH CHUYEN BAY HIEN CO ---");
                        for (Flight f : flights) {
                            System.out.println(f);
                        }
                        break;

                    case "2":
                        System.out.print("Nhap ma chuyen bay can kiem tra (vd: VN101, VJ202): ");
                        String checkCode = scanner.nextLine().trim();
                        int avail = service.availableSeats(checkCode);
                        if (avail >= 0) {
                            System.out.printf("[Ket qua] Chuyen bay %s hien con %d ghe trong.%n", checkCode.toUpperCase(), avail);
                        } else {
                            System.out.printf("[Ket qua] Khong tim thay ma chuyen bay %s!%n", checkCode);
                        }
                        break;

                    case "3":
                        System.out.print("Nhap ma chuyen bay muon dat: ");
                        String bookCode = scanner.nextLine().trim();
                        System.out.print("Nhap so luong ve muon dat: ");
                        try {
                            int bookSeats = Integer.parseInt(scanner.nextLine().trim());
                            if (bookSeats <= 0) {
                                System.out.println("So luong ghe phai > 0!");
                                break;
                            }
                            boolean bookOk = service.bookTicket(bookCode, bookSeats);
                            if (bookOk) {
                                System.out.printf("[Thanh cong] Ban da dat %d ve tren chuyen bay %s thanh cong!%n",
                                        bookSeats, bookCode.toUpperCase());
                                System.out.printf("So ghe con lai: %d%n", service.availableSeats(bookCode));
                            } else {
                                System.out.printf("[That bai] Khong the dat ve. Kiem tra lai ma chuyen bay hoac so ghe trong!%n");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("So luong ghe phai la so nguyen!");
                        }
                        break;

                    case "4":
                        System.out.print("Nhap ma chuyen bay muon huy: ");
                        String cancelCode = scanner.nextLine().trim();
                        System.out.print("Nhap so luong ve muon huy: ");
                        try {
                            int cancelSeats = Integer.parseInt(scanner.nextLine().trim());
                            if (cancelSeats <= 0) {
                                System.out.println("So luong ghe huy phai > 0!");
                                break;
                            }
                            boolean cancelOk = service.cancelBooking(cancelCode, cancelSeats);
                            if (cancelOk) {
                                System.out.printf("[Thanh cong] Ban da huy %d ve tren chuyen bay %s thanh cong!%n",
                                        cancelSeats, cancelCode.toUpperCase());
                                System.out.printf("So ghe con lai hien tai: %d%n", service.availableSeats(cancelCode));
                            } else {
                                System.out.printf("[That bai] Khong the huy ve. Kiem tra lai ma chuyen bay hoac so luong ve!%n");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("So luong ghe phai la so nguyen!");
                        }
                        break;

                    default:
                        System.out.println("Lua chon khong hop le, vui long chon lai!");
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("[Client] Loi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
