package bus_reservation;

import java.sql.*;

public class BusDAO {

    // ➤ Add Bus
    public void addBus(Bus b) {
        String sql = "INSERT INTO buses(bus_name, source, destination, seats) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {

            // Check connection
            if (conn == null) {
                System.out.println("❌ Connection Error!");
                return;
            }

            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, b.getName());
                ps.setString(2, b.getSource());
                ps.setString(3, b.getDestination());
                ps.setInt(4, b.getSeats());

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("✅ Bus Added Successfully");
                } else {
                    System.out.println("❌ Failed to Add Bus");
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error while adding bus!");
            e.printStackTrace();
        }
    }

    // ➤ View Buses
    public void viewBuses() {
        String sql = "SELECT * FROM buses";

        try (Connection conn = DBConnection.getConnection()) {

            // Check connection
            if (conn == null) {
                System.out.println("❌ Connection Error!");
                return;
            }

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                System.out.println("\n--- Available Buses ---");

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("bus_name");
                    String source = rs.getString("source");
                    String destination = rs.getString("destination");
                    int seats = rs.getInt("seats");

                    System.out.println(
                        id + " | " + name + " | " + source + " -> " + destination + " | Seats: " + seats
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error fetching buses!");
            e.printStackTrace();
        }
    }
}