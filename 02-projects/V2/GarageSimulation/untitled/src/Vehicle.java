abstract class Vehicle {

    private String model;
    private String brand;
    private String color;
    private boolean isFix;
    private double repairPrice; //explain this part
    private int isTofix;
    private double price;
    private int age;


    public Vehicle(String model, String color, boolean status, double repairPrice, double price, int age, String brand, int isTofix) {
        this.model = model;
        this.color = color;
        this.isFix = status;
        if (!isFix) {
            this.repairPrice = repairPrice;
            this.isTofix = isTofix;
        } else {
            this.repairPrice = (0.0);
            this.isTofix = (0);
        }
        this.price = price;
        this.age = age;
        this.brand=brand;

    }

    public void displayVehicleDetails() {
        System.out.println("Brand: "+brand);
        System.out.println("Model: " + model);
        System.out.println("Color: " + color);
        System.out.println("Fix: " + isFix);
        System.out.println("RepearPrice: " + repairPrice);
        System.out.println("Price: " + price);
        System.out.println("Age: " + age);

    }
    public void showFix(){ //
        if (isFix) {
            displayVehicleDetails(); //explain this part
        }
    }


    public void calcRepairPrice() {

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFix() {
        return isFix;
    }

    public void setFix(boolean fix) {
        isFix = fix;
    }

    public double getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(double repairPrice) {  //explain this part
        if(isFix()==false){
            this.repairPrice = repairPrice;
        }

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getIsTofix() {
        return isTofix;
    }

    public void setIsTofix(int isTofix) {
        this.isTofix = isTofix;
    }
}
