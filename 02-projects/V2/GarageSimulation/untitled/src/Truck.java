public class Truck extends FourWheeler{
    private String vehicleType;
    private  double cargoCapacity;


    public Truck(String model, String color, boolean status, double repearPrice, double price, int age,String brand,String motorcycleType, boolean eBike, double cargoCapacity, int isTofix) {
        super(model, color, status, repearPrice, price, age, brand, isTofix);
        this.vehicleType = "Truck";
        this.cargoCapacity = cargoCapacity;

    }

    @Override
    public void displayVehicleDetails() {
        super.displayVehicleDetails();
        System.out.println("Vehicle type: " + vehicleType);
        System.out.println("Vehicle capacity: " + cargoCapacity +" liters");
    }


    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }
}
