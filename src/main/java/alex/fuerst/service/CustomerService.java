package alex.fuerst.service;

import alex.fuerst.model.Customer;

import java.util.HashMap;
import java.util.Map;

/**
 * Singelton class that creates and manages customers.
 */
public class CustomerService {

    // this is the single-only instance of this class
    private static CustomerService customerService;

    // private constructor so other classes are restricted when instantiating this class
    private CustomerService() {}

    // returns the only instance of this class. This is the only possibility to instantiate the class
    public static CustomerService getInstance() {
        if (customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    // Stores all created customers in a map
    private Map<String, Customer> allCustomers = new HashMap<String, Customer>();

    /**
     * Creates a new customer.
     * {@link Customer} constructor contains regex for email validation.
     *
     * @param firstName
     * @param lastName
     * @param email
     * @return  Customer or Null, if email already exists
     * @throws  IllegalArgumentException if email is no valid
     */
    public Customer addCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer(firstName, lastName, email);
        if (allCustomers.containsKey(customer.getEmail())) {
            System.out.println("Email already exists!");
            return null;
        }
        else {
            allCustomers.put(email, customer);
            return customer;
        }
    }

    public Customer getCustomer(String customerEmail) {
        return allCustomers.get(customerEmail);
    }

    public void getAllCustomers() {
        for (Customer customer : allCustomers.values()) {
            System.out.println(customer);
        }
    }
}