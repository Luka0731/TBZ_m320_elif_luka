abstract class TwoWheeler  extends Vehicle {

    public int numberOfWheels;

    public TwoWheeler(String model, String color, boolean status, double repearPrice, double price, int age, String brand, int isTofix ) {
        super(model, color, status, repearPrice, price, age, brand, isTofix);
        this.numberOfWheels = 2;
    }

    @Override
    public void displayVehicleDetails() {
        super.displayVehicleDetails();
        System.out.println("Number of wheels: " + numberOfWheels);

    }

    @Override
    public void calcRepairPrice() {
        setRepairPrice(getIsTofix()*200);

    }
    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }


}