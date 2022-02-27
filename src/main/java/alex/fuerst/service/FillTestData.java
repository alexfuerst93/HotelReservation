package alex.fuerst.service;

import alex.fuerst.model.RoomType;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * Class for filling the reservation system with test data.
 */
public class FillTestData {

    private static FillTestData fillTestData;
    private FillTestData() {}
    public static FillTestData getInstance() {
        if (fillTestData == null) {
            fillTestData = new FillTestData();
        }
        return fillTestData;
    }

    private static boolean notFilled = true;

    /**
     * Reads multiple csv files from /src/main/resources directory.
     * <li>testRooms.csv for adding Rooms</li>
     * <li>testCustomers.csv for adding Customers</li>
     * <li>testReservations.csv for adding Reservations</li>
     * <br>
     * Can only be called once. Prints error message to console if called twice.
     */
    public void FillData() {
        if (notFilled) {
            read(Paths.get("src/main/resources/testRooms.csv"), "testRooms");
            System.out.println("Successfully added rooms.");

            read(Paths.get("src/main/resources/testCustomers.csv"), "testCustomers");
            System.out.println("Successfully added customers.");

            read(Paths.get("src/main/resources/testReservations.csv"), "testReservations");
            System.out.println("Successfully added reservations.");

            notFilled = false;
        }
        else {
            System.out.println("You already filled the system with Testdata!");
        }
    }

    private void read(Path path, String loadCsv) {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line = reader.readLine();
            do {
                String[] testData = line.split(";");
                switcher(testData, loadCsv);
                line = reader.readLine();
            }
            while (line != null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void switcher(String[] testData, String loadCsv) {
        switch (loadCsv) {
            case "testRooms" : createRooms(testData); break;
            case "testCustomers" : createCustomers(testData); break;
            case "testReservations" : createReservations(testData); break;
        }
    }

    private void createRooms(String[] testData) {
        String roomNumber = testData[0];
        Double price = Double.parseDouble(testData[1]);
        RoomType roomType;
        if (testData[2].equals("single")) {
            roomType = RoomType.SINGLE;
        }
        else {
            roomType = RoomType.DOUBLE;
        }
        ReservationService.getInstance().addRoom(roomNumber, price, roomType);
    }


    private void createCustomers(String[] testData) {
        String firstName = testData[0];
        String lastName = testData[1];
        String email = testData[2];
        CustomerService.getInstance().addCustomer(firstName, lastName, email);
    }

    private void createReservations(String[] testData) {
        String email = testData[0];
        String roomNumber = testData[1];
        LocalDate checkIn = LocalDate.parse(testData[2]);
        LocalDate checkOut = LocalDate.parse(testData[3]);
        ReservationService.getInstance().reserveRoom(email, roomNumber, checkIn, checkOut);
    }

}
