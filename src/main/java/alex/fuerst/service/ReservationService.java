package alex.fuerst.service;

import alex.fuerst.model.*;

import java.time.LocalDate;
import java.util.*;

public class ReservationService {

        private static ReservationService reservationService;
        private ReservationService() {}
        public static ReservationService getInstance() {
            if (reservationService == null) {
                reservationService = new ReservationService();
            }
            return reservationService;
        }

        // list of room objects
        List<IRoom> allRooms = new ArrayList<IRoom>();
        List<Reservation> allReservations = new ArrayList<Reservation>();

        public IRoom addRoom(String roomNumber, Double price, RoomType roomType) {
            IRoom room = null;
            if (price == 0.0) { room = new FreeRoom(roomNumber, roomType); }
            else { room = new Room(roomNumber, price, roomType); }
            // check if the new room is already in allRooms
            for (IRoom elem : allRooms) {
                if (elem.getRoomNumber().equals(room.getRoomNumber())) {
                    return null;
                }
            }
            allRooms.add(room);
            return room;
        }

        IRoom getRoom(String roomNumber) {
            for (IRoom room : allRooms) {
                if (room.getRoomNumber().equals(roomNumber)) {
                    return room;
                }
            }
            return null;
        }

        public void getAllRooms() {
            for (IRoom room : allRooms) {
                System.out.println(room);
            }
        }

        public List<IRoom> availableRooms(LocalDate checkInDate, LocalDate checkOutDate) {

            List<IRoom> available = new ArrayList<IRoom>(allRooms);

            // iterates over all reservations to find all rooms that are reserved based on the user's timeframe

            for (Reservation room : allReservations) {
                if (checkInDate.isAfter(room.getCheckInDate()) && checkInDate.isBefore(room.getCheckOutDate())) {
                    available.remove(room.getRoom());
                }
                else if (checkOutDate.isAfter(room.getCheckInDate()) && checkOutDate.isBefore(room.getCheckOutDate()))  {
                    available.remove(room.getRoom());
                }
                else if (checkInDate.isBefore(room.getCheckInDate()) && checkOutDate.isAfter(room.getCheckOutDate())) {
                    available.remove(room.getRoom());
                }
                else if (checkInDate.isEqual(room.getCheckInDate()) && checkOutDate.isEqual(room.getCheckOutDate())) {
                    available.remove(room.getRoom());
                }
            }
            return available;
        }

        public void reserveRoom(String userEmail, String userRoom, LocalDate checkInDate, LocalDate checkOutDate) {
            Customer user = CustomerService.getInstance().getCustomer(userEmail);
            IRoom room = getRoom(userRoom);
            // checks whether it is possible to reserve the room!
            if (!availableRooms(checkInDate, checkOutDate).contains(room)) {
                System.out.println("Unfortunately, this room is not available for selected time period.");
            }

            else {
                Reservation reservation = new Reservation(user, room, checkInDate, checkOutDate);
                allReservations.add(reservation);
                System.out.println("You successfully made a reservation: " + reservation);
            }
        }

        public void getCustomersReservation(String userEmail) {
            Customer customer = CustomerService.getInstance().getCustomer(userEmail);
            for (Reservation reservation : allReservations) {
                if (reservation.getCustomer().equals(customer)) {
                    System.out.println("Reservation for " + customer + " : " + reservation);
                }
            }
        }

        public void printAllReservations() {
            for (Reservation reservation : allReservations) {
                System.out.println(reservation);
            }
        }
}
