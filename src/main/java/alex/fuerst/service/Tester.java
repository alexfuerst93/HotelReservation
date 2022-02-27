package alex.fuerst.service;

import alex.fuerst.model.*;

public class Tester {
    public static void main(String[] args) {

        FillTestData.getInstance().FillData();
        for (IRoom room : ReservationService.getInstance().allRooms) {
            System.out.println(room);
        }
        CustomerService.getInstance().getAllCustomers();
        for (Reservation reservation : ReservationService.getInstance().allReservations) {
            System.out.println(reservation);
        }

        /*
        ReservationService.getInstance().addRoom("#123", 35.0, RoomType.DOUBLE);
        ReservationService.getInstance().addRoom("#123", 0.0, RoomType.SINGLE);
        ReservationService.getInstance().getAllRooms();

         */
        //FillTestData.getInstance().FillData();
        //System.out.println("-----------");

        /* Testing CustomerService
        System.out.println(CustomerService.getInstance().getCustomer("meyer@gmail.com"));
        System.out.println("---");
        CustomerService.getInstance().addCustomer("Alex", "Meyer", "cool@yahoo.com");
        CustomerService.getInstance().addCustomer("Alex", "Meyer", "cool@yahoo.com");
        CustomerService.getInstance().getAllCustomers();
        */

        /* Testing rooms
        ReservationService.getInstance().getAllRooms();
        System.out.println("---");
        System.out.println(ReservationService.getInstance().getRoom("#101"));
        System.out.println(ReservationService.getInstance().getRoom("#123"));
        System.out.println("---");
        */

        /* Testing reservations
        ReservationService.getInstance().printAllReservations();
        System.out.println("---");
        ReservationService.getInstance().getCustomersReservation("meyer@gmail.com");
         */

        // Testing Reservation-Logic;
        //ReservationService.getInstance().printAllReservations();
        //System.out.println(ReservationService.getInstance().availableRooms(LocalDate.parse("2022-01-18"), LocalDate.parse("2022-01-21")));
        //ReservationService.getInstance().printAllReservations();
        //ReservationService.getInstance().reserveRoom("meyer@gmail.com", "#101", LocalDate.parse("2022-01-20"), LocalDate.parse("2022-01-31"));
        //ReservationService.getInstance().reserveRoom("wicked@outlook.com", "#101", LocalDate.parse("2022-01-25"), LocalDate.parse("2022-01-28"));
        //System.out.println("----------");
        //ReservationService.getInstance().printAllReservations();
    }
}
