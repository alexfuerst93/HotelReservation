package api;

import model.Customer;
import model.IRoom;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

public class AdminResource {
    // for hotel staff only


    public Customer getOneCustomer(String customerEmail) {
        return CustomerService.getInstance().getCustomer(customerEmail);
    }

    public void getCustomers() {
        CustomerService.getInstance().getAllCustomers();
    }

    public void allRooms() {
        ReservationService.getInstance().getAllRooms();
    }

    public void addRoom(String roomNumber, Double price, RoomType roomType) {
        ReservationService.getInstance().addRoom(roomNumber, price, roomType);
    }

    public void displayAllReservations() { ReservationService.getInstance().printAllReservations(); }

}
