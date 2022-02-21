package service;

import model.FreeRoom;
import model.Reservation;
import model.Room;
import model.RoomType;

import java.time.LocalDate;

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

    public void FillData() {
        if (notFilled) {
            ReservationService.getInstance().addRoom("#101", 75.50, RoomType.SINGLE);
            ReservationService.getInstance().addRoom("#102", 49.95, RoomType.SINGLE);
            ReservationService.getInstance().addRoom("#205", 195.00, RoomType.DOUBLE);
            ReservationService.getInstance().addRoom("#401", 204.15, RoomType.DOUBLE);
            ReservationService.getInstance().addRoom("#311", 0.00, RoomType.DOUBLE);
            System.out.println("Successfully added rooms.");

            CustomerService.getInstance().addCustomer("Alex", "Meyer", "meyer@gmail.com");
            CustomerService.getInstance().addCustomer("John", "Wick", "wicked@outlook.com");
            CustomerService.getInstance().addCustomer("Lissy", "Smith", "smith@generic.com");
            System.out.println("Successfully added customers.");

            ReservationService.getInstance().reserveRoom("meyer@gmail.com", "#401", LocalDate.parse("2022-01-12"), LocalDate.parse("2022-01-19"));
            ReservationService.getInstance().reserveRoom("wicked@outlook.com", "#205", LocalDate.parse("2022-01-20"), LocalDate.parse("2022-01-31"));
            ReservationService.getInstance().reserveRoom("smith@generic.com", "#311", LocalDate.parse("2022-01-01"), LocalDate.parse("2022-01-31"));

            System.out.println("Successfully added reservations.");
            notFilled = false;
        }
        else {
            System.out.println("You already filled the system with Testdata!");
        }
    }

}
