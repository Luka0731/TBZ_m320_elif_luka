package ch.tbz.guessinggame;

import java.util.Scanner;
import java.util.Random;

public class GuessingGame {

    private static final int MAX_NUMBER = 10;
    private int secretNumber;

    public GuessingGame() {
        Random randomNumber = new Random();
        secretNumber = randomNumber.nextInt(MAX_NUMBER) + 1;
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        boolean guessed = false;

        System.out.println("Rate die Zahl zwischen 1 und " + MAX_NUMBER + ":");

        while (!guessed) {
            int guess = sc.nextInt();

            if (guess == secretNumber) {
                System.out.println("Richtig!");
                guessed = true;
            } else if (guess < secretNumber) {
                System.out.println("Zu klein!");
            } else {
                System.out.println("Zu gross!");
            }
        }
        sc.close();
    }
}