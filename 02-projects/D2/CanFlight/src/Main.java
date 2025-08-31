import java.util.Scanner;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scedule sl = new Scedule();
        System.out.println("Welcome to CanFlight!");
        System.out.println("OPTIONS: Book a flight :1");
        System.out.println("See all passengers :2");
        System.out.println("Search for a Person :3");
        System.out.println("Remove a person from a flight :4");
        System.out.println("EXIT: 0");
        int userChoice = 0;
        do {
            userChoice = sc.nextInt();
            switch (userChoice) {
                case 1: {
                    sl.registerUser();
                    break;
                }
                case 2: {
                    sl.showAll();
                    break;
                }
                case 3: {
                    sl.searchForpassenger();
                    break;
                }
                case 4: {
                    sl.removePassenger();
                    break;
                }
                case 0: {
                    System.out.println("Goodbye!");
                    return;
                }
                default: {
                    System.out.println("Invalid choice");
                }
            }

        } while (userChoice != 0);
        sc.close();
    }

}
