package bus_reservation;

import java.sql.*;

public class BookingDAO {

    public void bookTicket(int busId, String name, int seats) {

        if (seats <= 0) {
            System.out.println("❌ Invalid number of seats!");
            return;
        }

        String checkSQL = "SELECT seats FROM buses WHERE id=?";
        String insertSQL = "INSERT INTO bookings(bus_id, passenger_name, seats_booked) VALUES (?, ?, ?)";
        String updateSQL = "UPDATE buses SET seats = seats - ? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps1 = conn.prepareStatement(checkSQL)) {

            if (conn == null) {
                System.out.println("❌ Connection Error!");
                return;
            }

            ps1.setInt(1, busId);
            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                System.out.println("❌ Bus ID not found!");
                return;
            }

            int availableSeats = rs.getInt("seats");

            if (availableSeats < seats) {
                System.out.println("❌ Not enough seats available!");
                return;
            }

            try (PreparedStatement ps2 = conn.prepareStatement(insertSQL);
                 PreparedStatement ps3 = conn.prepareStatement(updateSQL)) {

                ps2.setInt(1, busId);
                ps2.setString(2, name);
                ps2.setInt(3, seats);
                ps2.executeUpdate();

                ps3.setInt(1, seats);
                ps3.setInt(2, busId);
                ps3.executeUpdate();

                System.out.println("✅ Ticket Booked Successfully!");

            }

        } catch (SQLException e) {
            System.out.println("❌ Error during booking!");
            e.printStackTrace();
        }
    }

    public void viewBookings() {
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (conn == null) {
                System.out.println("❌ Connection Error!");
                return;
            }

            System.out.println("\n--- Booking Details ---");
            while (rs.next()) {
                System.out.println(
                    "Booking ID: " + rs.getInt("id") +
                    ", Bus ID: " + rs.getInt("bus_id") +
                    ", Name: " + rs.getString("passenger_name") +
                    ", Seats: " + rs.getInt("seats_booked")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error fetching bookings!");
            e.printStackTrace();
        }
    }
}