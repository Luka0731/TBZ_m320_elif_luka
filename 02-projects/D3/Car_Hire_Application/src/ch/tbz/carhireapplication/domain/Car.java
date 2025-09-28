package ch.tbz.carhireapplication.domain;

public class Car extends Vehicle {
    private int seats;
    private String fuelType;

    public Car(String brand, String model, String licensePlate, int year, double fullPrice, int seats, String fuelType) {
        super(brand, model, licensePlate, year, fullPrice);
        this.seats = seats;
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "Car: " + getModel() + ", " + getBrand() + ", " + getYear() + ", " + fuelType + ", " + seats + " seats, " + getFullPrice() + " CHF";
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
