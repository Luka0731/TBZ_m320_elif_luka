package ch.tbz.carhireapplication.ui;

import ch.tbz.carhireapplication.domain.Rentable;
import ch.tbz.carhireapplication.service.PaymentGateway;
import ch.tbz.carhireapplication.util.PaymentGatewayRegistry;
import ch.tbz.carhireapplication.util.frontendSamples.ConsoleStyle;
import ch.tbz.carhireapplication.util.frontendSamples.Input;
import ch.tbz.carhireapplication.util.frontendSamples.Output;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Client {
    private final PaymentGatewayRegistry registry;
    private int appRunState;
    private PaymentGateway<? extends Rentable> activePaymentGateway;

    public Client(PaymentGatewayRegistry registry) {
        this.registry = registry;
    }

    public void run() {
        // Program welcome
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////");
        Output.printlnRainbowColored("Welcome to Car-Hire-Application!");

        // Main Loop
        appRunState = 1;
        while (appRunState == 1) {
            choosePaymentGateway();
            while (appRunState == 2) {
                int index = Output.menu(activePaymentGateway.getId() + " Menu", Arrays.asList("Choos Other Item", "Show", "Rent", "Cancel Rent"));
                switch (index) {
                    case 0: appRunState = 1; break;
                    case 1: showAllRentables(); break;
                    case 2: rentRentable(); break;
                    case 3: cancelRentable(); break;
                    default: break;
                }
            }
        }

        // Program bye
        System.out.println("Thank you for use this programm!");
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////");
    }

    private void choosePaymentGateway() {
        // Prepare important data
        List<PaymentGateway<? extends Rentable>> paymentGateways = registry.getAllPaymentGateways();
        List<String> options = registry.getAllIds();
        options.addFirst("Exit");

        System.out.println();
        int index = Output.menu("Choose a Rentable Item", options);
        System.out.println();
        if (index == 0) {
            appRunState = 0;
            return;
        }
        activePaymentGateway = paymentGateways.get(index - 1);
        appRunState = 2;
    }

    private void showAllRentables() {
        List<? extends Rentable> rentables = activePaymentGateway.getAll();
        System.out.println("\n--- " + activePaymentGateway.getId() + " -------------------------------------------");
        for (Rentable rentable : rentables) {
            System.out.println(rentable);
        }
        System.out.println("----------------------------------------------" + "-".repeat(activePaymentGateway.getId().length() + 2) + "\n");
    }

    private void rentRentable() {
        if (activePaymentGateway.getAllByAvailability(true).isEmpty()) {
            System.out.println("\nNothing is open for rent right now.\n");
            return;
        }

        // print all rentables
        List<? extends Rentable> availableRentables = activePaymentGateway.getAllByAvailability(true);
        System.out.println("\n--- Rentable Items -------------------------------------------");
        int index = 0;
        for (Rentable rentable : availableRentables) {
            Output.printlnStyled(++index + ") " + rentable, ConsoleStyle.GREEN);
        }
        System.out.println("--------------------------------------------------------------");

        // Renting the rentable
        index = Input.getInteger("Type in the number of the item that you want to rent: ", 1, index);
        UUID idOfWantedRentable = availableRentables.get(index - 1).getId();
        printRentInformation(activePaymentGateway.rentById(idOfWantedRentable));
        Output.printlnStyled("Rental was successful!\n", ConsoleStyle.GREEN);
    }

    // @@@@@@ this was done with ai @@@@@@
    private void printRentInformation(Map<String, String> rentInformation) {
        System.out.println("\n--- Rent Information -------------------------------------------");
        if (rentInformation == null || rentInformation.isEmpty()) {
            Output.printlnStyled("No details available.", ConsoleStyle.BLUE);
            System.out.println("--------------------------------------------------------------\n");
            return;
        }
        // optional title
        String policy = rentInformation.get("policy");
        if (policy != null && !policy.isBlank()) {
            Output.printlnStyled("Policy: " + policy, ConsoleStyle.BLUE);
        }
        // print all key/value pairs (policy already shown above)
        for (Map.Entry<String, String> e : rentInformation.entrySet()) {
            if ("policy".equalsIgnoreCase(e.getKey())) continue;
            Output.printlnStyled(e.getKey() + ": " + e.getValue(), ConsoleStyle.BLUE);
        }
        System.out.println("--------------------------------------------------------------\n");
    }

    private void cancelRentable() {
        if (activePaymentGateway.getAllByAvailability(false).isEmpty()) {
            System.out.println("\nNothing is being rented right now.\n");
            return;
        }

        // print all rented items
        List<? extends Rentable> rentedItems = activePaymentGateway.getAllByAvailability(false);
        System.out.println("\n--- Rented Items -------------------------------------------");
        int index = 0;
        for (Rentable rentable : rentedItems) {
            Output.printlnStyled(++index + ") " + rentable, ConsoleStyle.YELLOW);
        }
        System.out.println("-------------------------------------------------------------");

        // Cancel the rent
        index = Input.getInteger("Type in the number of the item that you want to cancel the rent: ", 1, index);
        UUID idOfWantedRentable = rentedItems.get(index - 1).getId();
        activePaymentGateway.cancelRentById(idOfWantedRentable);
        Output.printlnStyled("\nCancellation was successful!\n", ConsoleStyle.GREEN);
    }
}
