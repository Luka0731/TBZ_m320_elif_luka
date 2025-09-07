public class Motorcycle extends TwoWheeler {

    private String vehicle;
    private String motorcycleType;


    public Motorcycle(String model, String color, boolean status, double repearPrice, double price, int age, String motorcycleType, String brand, int isTofix) {
        super(model, color, status, repearPrice, price, age, brand, isTofix);
        this.vehicle = "Motorcycle";
        this.motorcycleType = motorcycleType;
    }


    @Override
    public void displayVehicleDetails() {
        System.out.println("Vehicle Type: " + vehicle);
        System.out.println("Motorcycle Type: " + motorcycleType);
        super.displayVehicleDetails();

    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getMotorcycleType() {
        return motorcycleType;
    }

    public void setMotorcycleType(String motorcycleType) {
        this.motorcycleType = motorcycleType;
    }
}
