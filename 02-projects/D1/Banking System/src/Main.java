package Banking_System;

import Banking_System.System_Logic.System_Logic;
import Banking_System.User.Customer;
import Banking_System.User.CustomerList;
import Banking_System.User_Interaction.ControllSystem;

public class Main {
    public static void main(String[] args) {
        CustomerList sharedList = new CustomerList();  //That's the main part which connects everything actually
        ControllSystem controllSystem = new ControllSystem(sharedList);
        controllSystem.reDirection();
    }
}