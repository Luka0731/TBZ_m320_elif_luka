package Banking_System.System_Logic;
import Banking_System.System_Security.SecureMethods;
import Banking_System.User.Customer;
import Banking_System.User.CustomerList;

import java.util.*;

public class System_Logic {
    Scanner sc = new Scanner(System.in);
    CustomerList cl;
    ArrayList<Customer> customer_list;

    public System_Logic(CustomerList cl) {
        this.cl = cl;
    }
    public Customer createCustomer() { //for a more controlled process
        int secureCheck = 0; //for security reasons

        System.out.println("Welcome to CanBanking");
        System.out.println("Please enter your age:");
        int age = sc.nextInt();
        if (age > 120 || age < 14) {
            do {
                System.out.println("Please enter a valid age");
                age = sc.nextInt();
                secureCheck++;
                if (secureCheck < 5) {
                    return null;
                }
            } while (age > 120 || age < 14);
        }
        System.out.println("Please enter your firstname:");
        String customerName = sc.next();
        System.out.println("Please enter your lastname:");
        String customerLastName = sc.next();
        System.out.println("Please enter your email:");
        String customerEmail = sc.next();
        if (!SecureMethods.validateEmail(customerEmail)) {
            do {
                System.out.println("Please enter a valid email:");
                customerEmail = sc.next();
                secureCheck++;
                if (secureCheck > 5) {
                    return null;
                }
            } while (!SecureMethods.validateEmail(customerEmail));
        }

        System.out.println("Please enter your password:");
        String password = sc.next();
        if (!SecureMethods.validatePassword(password)) {
            do {
                System.out.println("Please enter a valid password:");
                password = sc.nextLine();
                secureCheck++;
                if (secureCheck > 5) {
                    return null;
                }
            } while (!SecureMethods.validatePassword(password));
        }

        Customer customer = new Customer(customerName, customerLastName, customerEmail, password, age);
        cl.setCustomer_list(customer); //Doubt if that's a nice way to do it
        return customer;
    }





}
