public class Ovomaltine implements iBuyable{
    public void price(int kg) {
        int price = kg*13;
        System.out.println(price+" CHF");
    }

}
