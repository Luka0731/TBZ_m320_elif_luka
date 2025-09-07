abstract class FourWheeler extends Vehicle {

    public int numberOfWheels;

    public FourWheeler(String model, String color, boolean status, double repearPrice, double price, int age, String brand, int isTofix) {
        super(model,color,status,repearPrice,price,age,brand,isTofix);
        this.numberOfWheels = 4;
    }
    @Override
    public void displayVehicleDetails() {
        super.displayVehicleDetails();
        System.out.println("Number of wheels: " + numberOfWheels);

    }
    @Override
    public void calcRepairPrice() {
        setRepairPrice(getIsTofix()*600); //Assuming its gonna be more expensive

}}
