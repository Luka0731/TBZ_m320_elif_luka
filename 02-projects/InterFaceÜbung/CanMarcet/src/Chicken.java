public class Chicken implements iBuyable{

    public void price(int kg) {
        int price = kg*20;
        System.out.println(price+" CHF");
    }
}
