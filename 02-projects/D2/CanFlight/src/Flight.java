import java.time.LocalDateTime;
import java.util.*;
public class Flight {

String airLines;
LocalDateTime departure;
String destination;
String flightNumber;
ArrayList<Passenger> passengers;

public Flight(String airLines, String departure, String destination, String flightNumber, ArrayList<Passenger> passengers) {
    this.airLines = airLines;
    this.departure = LocalDateTime.parse(departure);
    this.destination = destination;
    this.flightNumber = flightNumber;
    this.passengers = passengers;
}

    public String getAirLines() {
        return airLines;
    }

    public void setAirLines(String airLines) {
        this.airLines = airLines;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }
}
