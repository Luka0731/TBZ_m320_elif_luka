package ch.tbz.carhireapplication.domain;

import java.util.UUID;

public abstract class Vehicle implements Rentable {
    private UUID id;
    private String brand;
    private String model;
    private String licensePlate;
    private int year;
    private boolean isAvailable;
    private double fullPrice;

    protected Vehicle(String brand, String model, String licensePlate, int year, double fullPrice) {
        this.id = UUID.randomUUID();
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.year = year;
        this.isAvailable = true;
        this.fullPrice = fullPrice;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void setIsAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }
}
