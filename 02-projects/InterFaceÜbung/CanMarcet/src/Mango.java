public class Mango implements iBuyable{

    public void price(int kg) {
        int price = kg*80;
        System.out.println(price+" CHF");
    }
}
