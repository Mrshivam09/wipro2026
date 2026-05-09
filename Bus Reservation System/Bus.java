package bus_reservation;

public class Bus {
    private int id;
    private String name;
    private String source;
    private String destination;
    private int seats;

    public Bus() {}

    public Bus(String name, String source, String destination, int seats) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.seats = seats;
    }

    public Bus(int id, String name, String source, String destination, int seats) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.seats = seats;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public int getSeats() { return seats; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSource(String source) { this.source = source; }
    public void setDestination(String destination) { this.destination = destination; }
    public void setSeats(int seats) { this.seats = seats; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + source + " -> " + destination + " | Seats: " + seats;
    }
}
