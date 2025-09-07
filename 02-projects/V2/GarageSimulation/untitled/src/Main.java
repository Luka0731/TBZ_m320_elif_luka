import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        ArrayList<TwoWheeler> tw = new ArrayList<TwoWheeler>();
        ArrayList<FourWheeler> fw = new ArrayList<FourWheeler>();
        Scanner sc = new Scanner(System.in);
        tw.add(new Bicycle(
                "Canyon Ultimate CF SL",
                "Red",
                false,
                0.00,
                2500.00,
                1,
                "Canyon",
                "Road Bike",
                false,
                4
        ));

        tw.add(new Bicycle(
                "Specialized Turbo Vado",
                "Black",
                true,
                0,
                3200.00,
                2,
                "Specialized",
                "City E-Bike",
                true,
                0
        ));


        tw.add(new Motorcycle(
                "Yamaha MT-07",
                "Blue",
                false,
                0.00,
                7500.00,
                2,
                "Naked",
                "Yamaha",
                7
        ));

        tw.add(new Motorcycle(
                "Harley-Davidson Fat Boy",
                "Black",
                true,
                0,
                18500.00,
                5,
                "Cruiser",
                "Harley-Davidson",
                0
        ));

        fw.add(new Car(
                "Toyota Corolla",
                "White",
                false,
                0.00,
                22000.00,
                1,
                "Toyota",
                5,
                "Sedan",
                4
        ));

        fw.add(new Car(
                "BMW X5",
                "Grey",
                true,
                0,
                60000.00,
                4,
                "BMW",
                5,
                "SUV",
                0
        ));


        fw.add(new Truck(
                "Mercedes Actros",
                "White",
                true,
                3000.00,
                20000.00,
                6,
                "Mercedes-Benz",
                "None",
                false,
                18000.00,
                0
        ));

        fw.add(new Truck(
                "Volvo FH16",
                "Blue",
                false,
                0.00,
                140000.00,
                3,
                "Volvo",
                "None",
                false,
                20000.00,
                2
        ));

        vehicles.addAll(fw);
        vehicles.addAll(tw);

        do {
            System.out.println("To see all the vehicles which need to be repaired: 0\nTo sort from the lowest to the highest : 1\nTo see all vehicles:2 ");
            int userChoice = sc.nextInt();
            switch (userChoice) {
                case 0: {
                    for (Vehicle vehicle : vehicles) {
                        vehicle.calcRepairPrice();
                    }
                    for (Vehicle vehicle : vehicles) {
                        if (!vehicle.isFix()) {
                            vehicle.displayVehicleDetails();
                            System.out.println("--------------------------");
                        }
                    }
                    break;
                }
                case 1: {
                    vehicles.sort(Comparator.comparingDouble(Vehicle::getPrice));//By AI
                    for (Vehicle vehicle : vehicles) {
                        vehicle.displayVehicleDetails();
                        System.out.println("--------------------------");
                    }
                    break;
                }
                case 2: {
                    for (Vehicle vehicle : vehicles) {
                        vehicle.displayVehicleDetails();
                        System.out.println("--------------------------");
                    }
                    break;
                }


                default: {
                    return;
                }
            }


        } while (true);

    }

}