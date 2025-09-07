public class Car extends FourWheeler{

    private String vehicleType;
    private int seatNumber;
    private String carType;



    public Car(String model, String color, boolean status, double repearPrice, double price, int age,String brand, int seatNumber, String carType,  int isTofix) {
        super(model, color, status, repearPrice, price, age, brand,  isTofix);
        this.vehicleType = "Car";
        this.seatNumber = seatNumber;
        this.carType = carType;
    }

    @Override
    public void displayVehicleDetails() {
        System.out.println("VehicleType: " + getVehicleType());
        System.out.println("Seat Number: " + getSeatNumber());
        System.out.println("Car Type: " + getCarType());
        super.displayVehicleDetails();
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
    public String getVehicleType() {
        return vehicleType;
    }
}
