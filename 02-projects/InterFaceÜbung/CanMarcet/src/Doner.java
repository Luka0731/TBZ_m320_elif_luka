public class Doner  implements iBuyable{

    public void price(int quantity) {
        int price = quantity*120;
        System.out.println(price+" CHF");
    }

}
