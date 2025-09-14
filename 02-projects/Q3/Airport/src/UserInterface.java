import java.util.HashMap;
import java.util.Scanner;

public class UserInterface {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AllAirports allAirports = new AllAirports();
        MapMethods mapMethods = new MapMethods();
        AllAirports aa = new AllAirports();
        TransferData td = new TransferData();
        HashMap hashMap = new HashMap();
        HashMap<String, String> airports = td.transferData(hashMap);
        int userChoice = 0;
        System.out.println("Airport list");

        do {
            System.out.println("Show all:1, Search:2, Add:3, Remove:4, End:0");
            System.out.println("Please enter your choice:");
            userChoice = scanner.nextInt();
            switch (userChoice) {

                case 1: {
                    mapMethods.showAll(airports);
                    break;
                }
                case 2: {
                    mapMethods.search(airports);
                    break;
                }
                case 3: {
                    airports = mapMethods.addAirport(airports);
                    break;
                }
                case 4: {
                    airports = mapMethods.removeAirport(airports);
                    break;
                }
                case 0: {
                    return;
                }
                default: {
                    System.out.println("Invalid choice");
                }

            }

        } while (userChoice != 0);


    }


}
