public class Bicycle extends TwoWheeler{

    private String vehicleType;
    private boolean eBike;

    public Bicycle(String model, String color, boolean status, double repearPrice, double price, int age,String brand,String motorcycleType, boolean eBike, int isTofix) {
        super(model, color, status, repearPrice, price, age, brand, isTofix);
        this.vehicleType = "Bicycle";
        this.eBike = eBike;

    }

   @Override
   public void displayVehicleDetails() {
       super.displayVehicleDetails();
       System.out.println("Vehicle Type: " + vehicleType);
       System.out.println("Ebike Status: " + eBike);

   }


    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean iseBike() {
        return eBike;
    }

    public void seteBike(boolean eBike) {
        this.eBike = eBike;
    }

}
