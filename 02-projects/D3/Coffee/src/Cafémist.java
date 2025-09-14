public class Cafémist implements Coffee{

    private int quantity;
    private int price; //per Kg
    private String name;

    public Cafémist(int quantity){
        this.quantity = quantity;
        this.price = 14;
        this.name="Cafémist";

    }


    public void displayInfo(){
        System.out.println("Name: "+name);
        System.out.println("Quantity: "+quantity);
        System.out.println("Price: "+price);

    }
    public int calcPrice(int quantity){
        return this.quantity*this.price;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
