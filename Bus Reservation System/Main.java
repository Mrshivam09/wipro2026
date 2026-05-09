package bus_reservation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BusDAO busDAO = new BusDAO();
        BookingDAO bookingDAO = new BookingDAO();

        while (true) {
            try {
                System.out.println("\n===== Bus Reservation System =====");
                System.out.println("1. View Buses");
                System.out.println("2. Book Ticket");
                System.out.println("3. View Bookings");
                System.out.println("4. Exit");

                System.out.print("Enter Choice: ");
                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        busDAO.viewBuses();
                        break;

                    case 2:
                        System.out.print("Enter Bus ID: ");
                        int id = sc.nextInt();

                        System.out.print("Passenger Name: ");
                        String pname = sc.next();

                        System.out.print("Seats to Book: ");
                        int s = sc.nextInt();

                        bookingDAO.bookTicket(id, pname, s);
                        break;

                    case 3:
                        bookingDAO.viewBookings();
                        break;

                    case 4:
                        System.out.println("👋 Exiting...");
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("❌ Invalid Choice!");
                }

            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid Input! Enter correct data.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("❌ Unexpected Error!");
                e.printStackTrace();
            }
        }
    }
}
