package alex.fuerst.api;


import alex.fuerst.model.Customer;
import alex.fuerst.model.IRoom;
import alex.fuerst.service.CustomerService;
import alex.fuerst.service.ReservationService;

import java.time.LocalDate;
import java.util.List;

public class HotelResource {
    // for public use

    public Customer createAccount(String firstName, String lastName, String email) {
        return CustomerService.getInstance().addCustomer(firstName, lastName, email);
    }

    public List<IRoom> searchForAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        return ReservationService.getInstance().availableRooms(checkInDate, checkOutDate);
    }

    public List<IRoom> extendedSearch(LocalDate checkInDate, LocalDate checkOutDate) {
        return ReservationService.getInstance().availableRooms(checkInDate.plusDays(7), checkOutDate.plusDays(7));
    }

    // user provides his Email and RoomNumber to reserve a room
    public void reserveRoom(String userEmail, String userRoom, LocalDate checkIn, LocalDate checkOut) {
        ReservationService.getInstance().reserveRoom(userEmail, userRoom, checkIn, checkOut);
    }

    public void getCustomerReservation(String userEmail) {
        ReservationService.getInstance().getCustomersReservation(userEmail);
    }


}
