package cli;

import api.AdminResource;
import model.Customer;

public class AdminMenu {

    public void getNavigation() {
        System.out.println("Admin Menu");
        System.out.println("------------------------------------");
        System.out.println("1. See all customers.");
        System.out.println("2. See all rooms.");
        System.out.println("3. See all reservations.");
        System.out.println("4. Add a room.");
        System.out.println("5. Populate application with test data.");
        System.out.println("6. Back to the main menu.");
        System.out.println("------------------------------------");
        System.out.println("Please select a number for the menu option.");
    }
}
