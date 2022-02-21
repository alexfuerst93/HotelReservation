package model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Tester {
    public static void main(String[] args) {

        // if they have the same email (doesnt matter if names are different), then two objects are equal
        Customer customer1 = new Customer("alex", "fuerst", "alex.fuerst@yahoo.com");
        Customer customer2 = new Customer("hans", "hoedl", "alex.fuerst@yahoo.com");
        //System.out.println(customer1.equals(customer2));

        //System.out.println(customer1.hashCode());
        //System.out.println(customer2.hashCode());
        System.out.println(customer1);

/*
        IRoom room1 = new Room("#123", 35.50, RoomType.SINGLE);
        IRoom room2 = new FreeRoom("#123", RoomType.SINGLE);

        System.out.print(room1.getRoomNumber().equals(room2.getRoomNumber()));

 */

        /*
        FreeRoom freeRoom = new FreeRoom("#123", RoomType.SINGLE);
        System.out.println(freeRoom);

         */

        /*
        // playing around with dates
        // yyyy-mm-dd
        LocalDate fromDate = LocalDate.parse("2021-01-12");
        fromDate = fromDate.plusDays(3);
        if (fromDate.isAfter(LocalDate.parse("2021-01-15"))) { System.out.println("True!"); } else { System.out.println("Flase!"); }
        LocalDate toDate = LocalDate.parse("2021-01-18");
        Reservation reservation = new Reservation(customer, freeRoom, fromDate, toDate);
        System.out.print(reservation);
         */
    }
}
