package alex.fuerst.cli;

import alex.fuerst.api.AdminResource;
import alex.fuerst.api.HotelResource;
import alex.fuerst.model.Customer;
import alex.fuerst.model.IRoom;
import alex.fuerst.model.RoomType;
import alex.fuerst.service.FillTestData;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MainMenu main = new MainMenu();
        AdminMenu admin = new AdminMenu();
        AdminResource adminResource = new AdminResource();
        HotelResource hotelResource = new HotelResource();
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        // starting application
        System.out.println("Welcome to our Hotel Reservation platform!");
        String userEmail = null;
        boolean running = true;

        while (running) {
            try {
                main.getNavigation();
                int userInput = scanner.nextInt();

                switch (userInput) {
                    case 1:
                        if (userEmail == null) {
                            System.out.println("You need to create an account first.");
                            continue;
                        }
                        scanner.nextLine();
                        System.out.println("When would you like to arrive (yyyy-mm-dd)?");
                        LocalDate checkInDate = LocalDate.parse(scanner.nextLine());
                        System.out.println("When would you like to depart (yyyy-mm-dd)?");
                        LocalDate checkOutDate = LocalDate.parse(scanner.nextLine());

                        List<IRoom> available = hotelResource.searchForAvailableRooms(checkInDate, checkOutDate);
                        if (available.isEmpty()) {

                            String choice = null;
                            do {
                                System.out.println("Unfortunately, there are no available rooms. Do you want to extend your search? (y/n)");
                                String userChoice = scanner.nextLine();
                                if (userChoice.equals("y") || userChoice.equals("n")) {
                                    choice = userChoice;
                                }
                            }
                            while (choice == null);

                            if (choice.equals("n")) {
                                continue;
                            }
                            else {
                                checkInDate = checkInDate.plusDays(7);
                                checkOutDate = checkOutDate.plusDays(7);
                                List<IRoom> extended = hotelResource.searchForAvailableRooms(checkInDate, checkOutDate);
                                if (extended.isEmpty()) {
                                    System.out.println("Sorry, still no rooms available!");
                                    continue;
                                }
                                else {
                                    System.out.println("Your available rooms: ");
                                    for (IRoom room : extended) {
                                        System.out.println(room);
                                    }
                                    System.out.println("Type the room number you would like to reserve: ");
                                    String roomId = scanner.nextLine();
                                    hotelResource.reserveRoom(userEmail, roomId, checkInDate, checkOutDate);
                                }
                            }
                        }
                        else {
                            System.out.println("Your available rooms: ");
                            for (IRoom room : available) {
                                System.out.println(room);
                            }
                            System.out.println("Type the room number you would like to reserve: ");
                            String roomId = scanner.nextLine();
                            hotelResource.reserveRoom(userEmail, roomId, checkInDate, checkOutDate);
                        }
                        continue;

                    case 2:
                        if (userEmail == null) {
                            System.out.println("You need to create an account first.");
                            continue;
                        }
                        hotelResource.getCustomerReservation(userEmail);
                        continue;

                    case 3:
                        scanner.nextLine();
                        System.out.println("Enter a valid email address (like 'john.smith@gmail.com': ");
                        String newEmail = scanner.nextLine();
                        System.out.println("Enter your First Name: ");
                        String userFirstName = scanner.nextLine();
                        System.out.println("Enter your Last Name: ");
                        String userLastName = scanner.nextLine();
                        try {
                            Customer customer = hotelResource.createAccount(userFirstName, userLastName, newEmail);
                            userEmail = customer.getEmail();
                        }
                        catch (IllegalArgumentException ex) {
                            System.out.println(ex.getLocalizedMessage());
                        }
                        continue;

                        // enters admin menu
                    case 4:
                        boolean adminPanel = true;
                        while (adminPanel) {
                            admin.getNavigation();
                            int adminInput = scanner.nextInt();
                            switch (adminInput) {
                                case 1:
                                    adminResource.getCustomers();
                                    continue;
                                case 2:
                                    adminResource.allRooms();
                                    continue;
                                case 3:
                                    adminResource.displayAllReservations();
                                    continue;
                                case 4:
                                    // getting rid of the leftover from the last scanner.nextInt()
                                    scanner.nextLine();

                                    System.out.println("Enter a room number: ");
                                    String roomNumber = scanner.nextLine();
                                    System.out.println("Enter a price. (Type '0.0' for creating a free Room): ");
                                    Double price = scanner.nextDouble();
                                    scanner.nextLine();

                                    RoomType roomType = null;
                                    do {
                                        System.out.println("Is it a single or double bedroom? (single/double): ");
                                        String userRoomType = scanner.nextLine();
                                        if (userRoomType.equals("single")) {
                                            roomType = RoomType.SINGLE;
                                        } else if (userRoomType.equals("double")) {
                                            roomType = RoomType.DOUBLE;
                                        }
                                    }
                                    while (roomType == null);

                                    adminResource.addRoom(roomNumber, price, roomType);
                                    continue;
                                case 5:
                                    FillTestData.getInstance().FillData();
                                    continue;
                                case 6:
                                    adminPanel = false;
                                    break;
                                default:
                                    System.out.println("Please enter a number between 1 and 6.");
                            }
                        }
                        continue;

                    case 5:
                        running = false;
                        break;

                    default:
                        System.out.println("Please enter a number between 1 and 5.");

                }
            } catch (InputMismatchException ex) {
                String invalidData = scanner.nextLine();
                System.out.println("You entered invalid data: " + invalidData);
            }
            catch (DateTimeParseException ex) {
                System.out.println("Enter your date in this format: yyyy-mm-dd");
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
        System.out.println("Thank you for your visit. Bye!");
    }
}
