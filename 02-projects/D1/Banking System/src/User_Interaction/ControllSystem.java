package Banking_System.User_Interaction;

import Banking_System.System_Logic.System_Logic;
import Banking_System.User.Customer;
import Banking_System.User.CustomerList;

import java.util.*;

public class ControllSystem {
    Scanner sc = new Scanner(System.in);
    CustomerList cl = new CustomerList();

    public ControllSystem(CustomerList cl) {
        this.cl = cl;
    }

    public void reDirection() {
        System_Logic sl = new System_Logic(cl);
        boolean flag = true;
        int dec = 0;
        do {
            System.out.println("Creating a customer : 1\n" +
                    "Log in : 2\n" +
                    "Exit : 0");
            System.out.println("Please enter your decision");
            dec = sc.nextInt();
            switch (dec) {
                case 1: {
                    Customer customer1 = sl.createCustomer(); //make sure not to allow dupplicate emails
                    detailedView(customer1);
                    break;
                }
                case 2: {
                    System.out.println("Please enter your first name");
                    String firstName = sc.next();
                    System.out.println("Please enter your last name");
                    String lastName = sc.next();
                    System.out.println("Please enter your email");
                    String email = sc.next();
                    System.out.println("Please enter your age");
                    int age = sc.nextInt();
                    System.out.println("Please enter your password");
                    String password = sc.next();

                    boolean found = false;
                    for (Customer c : cl.getCustomer_list()) {
                        if (c.getEmail().equals(email) &&
                                c.getFirstName().equalsIgnoreCase(firstName) &&
                                c.getLastName().equalsIgnoreCase(lastName) &&
                                c.getAge() == age &&
                                c.getPassword().equals(password)) {

                            detailedView(c);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Login failed. Please try again.");
                    }
                    break;
                }


                case 0: {
                    return;

                }

                default: {
                    System.out.println("Please enter a valid decision");
                    flag = false;
                    break;
                }
            }
        } while (!flag || dec != 0);


    }


    public void detailedView(Customer customer) {
        int checkIn = 0;
        do {
            System.out.println("Welcome " + customer.getFirstName() + " " + customer.getLastName() + "\n" +
                    "Your Balance: " + customer.getTotalAmount());
            System.out.println(
                    "Check in money : 1\n" +
                            "Check out money : 2\n" +
                            "Transfer money to another account : 3\n" +
                            "Exit : 0 ");

            checkIn = sc.nextInt();

            switch (checkIn) {
                case 1: {
                    double checkOut = 0;
                    do {
                        System.out.println("Please enter the amount you want to check in");
                        checkOut = sc.nextDouble();
                        if (checkOut == 0.0) {
                            System.out.println("Please enter a valid amount");
                        } else {
                            customer.setTotalAmount(customer.getTotalAmount() + checkOut);
                            System.out.println("You have successfully checked in. New balance: " + customer.getTotalAmount());
                        }
                    } while (checkOut == 0.0);
                    break;
                }
                case 2: {
                    double checkOut = 0;
                    do {
                        System.out.println("Please enter the amount you want to check out");
                        checkOut = sc.nextDouble();
                        if (checkOut == 0.0) {
                            System.out.println("Please enter a valid amount");
                        } else if (checkOut > customer.getTotalAmount()) {
                            System.out.println("Please enter a smaller amount than your total amount");
                            checkOut = 0.0;
                        } else {
                            customer.setTotalAmount(customer.getTotalAmount() - checkOut);
                            System.out.println("You have successfully checked out. New balance: " + customer.getTotalAmount());
                        }
                    } while (checkOut == 0.0);
                    break;
                }
                case 3: {
                    double checkOut = 0;
                    do {
                        System.out.println("Please enter the amount you want to transfer");
                        checkOut = sc.nextDouble();
                        if (checkOut == 0.0 || checkOut > customer.getTotalAmount()) {
                            System.out.println("Please enter a valid amount");
                            checkOut = 0.0;
                        } else {
                            System.out.println("Please enter the firstname you want to transfer to");
                            String firstName = sc.next();
                            System.out.println("Please enter the lastname you want to transfer to");
                            String lastName = sc.next();
                            System.out.println("Please enter the email");
                            String email = sc.next();

                            boolean transferred = false;
                            for (Customer c : cl.getCustomer_list()) {
                                if (c.getEmail().equalsIgnoreCase(email) &&
                                        c.getFirstName().equalsIgnoreCase(firstName) &&
                                        c.getLastName().equalsIgnoreCase(lastName)) {

                                    customer.setTotalAmount(customer.getTotalAmount() - checkOut);
                                    c.setTotalAmount(c.getTotalAmount() + checkOut);
                                    System.out.println("Transfer successful to " + c.getFirstName() + ". Your new balance: " + customer.getTotalAmount());
                                    transferred = true;
                                    break;
                                }
                            }
                            if (!transferred) {
                                System.out.println("Recipient not found.");
                            }
                        }
                    } while (checkOut == 0.0);
                    break;
                }
                case 0: {
                    reDirection();
                    break;
                }
                default: {
                    System.out.println("Please enter a valid option");
                    break;
                }
            }
        } while (checkIn != 0);
    }


}
