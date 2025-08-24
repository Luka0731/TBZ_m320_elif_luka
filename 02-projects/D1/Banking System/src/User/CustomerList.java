package Banking_System.User;

import java.util.ArrayList;

public class CustomerList {
    ArrayList<Customer> customer_list;

    public CustomerList() {
        customer_list = new ArrayList<>();
    }

    public ArrayList<Customer> getCustomer_list() {
        return customer_list;
    }

    public void setCustomer_list(Customer customer) {
        customer_list.add(customer);
        this.customer_list = customer_list;
    }




}
