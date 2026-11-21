
import java.util.*;
import java.io.*;

// ROOM CLASS
class Room {
    int roomNumber;
    String roomType;
    double pricePerNight;
    boolean available;

    Room(int roomNumber, String roomType,
         double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.available = true;
    }

    void displayRoomDetails() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room Type: " + roomType);
        System.out.println("Price: Rs. " + pricePerNight);
        System.out.println("Available: " + available);
    }
}

// GUEST CLASS
class Guest {
    String name;
    String phone;

    Guest(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}

// BOOKING CLASS
class Booking {
    int bookingId;
    Guest guest;
    Room room;
    int nights;
    double totalAmount;

    Booking(int bookingId, Guest guest,
            Room room, int nights) {

        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.nights = nights;

        totalAmount = room.pricePerNight * nights;
    }

    void displayBookingDetails() {
        System.out.println("\n----- BOOKING DETAILS -----");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Guest: " + guest.name);
        System.out.println("Phone: " + guest.phone);
        System.out.println("Room: " + room.roomNumber);
        System.out.println("Room Type: " + room.roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Total Amount: Rs. " + totalAmount);
    }
}

// PAYMENT CLASS
class Payment {

    boolean processPayment(double amount) {
        System.out.println("\n----- PAYMENT -----");
        System.out.println("Total Amount: Rs. " + amount);
        System.out.println("Payment successful (simulation)!");
        return true;
    }
}

// HOTEL CLASS
class Hotel {

    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();

    Hotel() {
        rooms.add(new Room(101, "Standard", 1200));
        rooms.add(new Room(102, "Standard", 1200));
        rooms.add(new Room(201, "Deluxe", 2200));
        rooms.add(new Room(202, "Deluxe", 2200));
        rooms.add(new Room(301, "Suite", 4000));
    }

    void displayRooms() {
        System.out.println("\n----- AVAILABLE ROOMS -----");

        for (Room room : rooms) {
            room.displayRoomDetails();
            System.out.println("--------------------");
        }
    }

    void searchRooms(String type) {
        boolean found = false;

        for (Room room : rooms) {
            if (room.roomType.equalsIgnoreCase(type)
                    && room.available) {
                room.displayRoomDetails();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available rooms found!");
        }
    }

    void bookRoom(int bookingId, String name,
                  String phone, int roomNumber,
                  int nights) {

        for (Room room : rooms) {

            if (room.roomNumber == roomNumber
                    && room.available && nights > 0) {

                Guest guest = new Guest(name, phone);

                Booking booking = new Booking(
                        bookingId, guest, room, nights);

                Payment payment = new Payment();

                if (payment.processPayment(booking.totalAmount)) {

                    room.available = false;
                    bookings.add(booking);

                    booking.displayBookingDetails();
                    saveBookings();
                    return;
                }
            }
        }

        System.out.println("Room not available or invalid booking!");
    }

    void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found!");
            return;
        }

        for (Booking booking : bookings) {
            booking.displayBookingDetails();
        }
    }

    void cancelBooking(int bookingId) {

        Iterator<Booking> iterator = bookings.iterator();

        while (iterator.hasNext()) {
            Booking booking = iterator.next();

            if (booking.bookingId == bookingId) {
                booking.room.available = true;
                iterator.remove();

                saveBookings();

                System.out.println("Booking cancelled successfully!");
                return;
            }
        }

        System.out.println("Booking not found!");
    }

    void saveBookings() {
        try (PrintWriter writer =
                     new PrintWriter(new FileWriter("bookings.txt"))) {

            for (Booking booking : bookings) {
                writer.println(
                        booking.bookingId + "," +
                                booking.guest.name + "," +
                                booking.guest.phone + "," +
                                booking.room.roomNumber + "," +
                                booking.nights + "," +
                                booking.totalAmount
                );
            }

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}

// MAIN CLASS
public class HotelReservationSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        int bookingId = 1;
        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("    HOTEL RESERVATION SYSTEM");
            System.out.println("================================");

            System.out.println("1. Display Rooms");
            System.out.println("2. Search Rooms");
            System.out.println("3. Book Room");
            System.out.println("4. View Bookings");
            System.out.println("5. Cancel Booking");
            System.out.println("6. Exit");

            System.out.print("\nEnter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    hotel.displayRooms();
                    break;

                case 2:
                    System.out.print("Enter Room Type: ");
                    String type = sc.nextLine();
                    hotel.searchRooms(type);
                    break;

                case 3:
                    System.out.print("Enter Guest Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();

                    System.out.print("Enter Room Number: ");
                    int room = sc.nextInt();

                    System.out.print("Enter Nights: ");
                    int nights = sc.nextInt();

                    hotel.bookRoom(
                            bookingId++, name,
                            phone, room, nights);
                    break;

                case 4:
                    hotel.viewBookings();
                    break;

                case 5:
                    System.out.print("Enter Booking ID: ");
                    int id = sc.nextInt();
                    hotel.cancelBooking(id);
                    break;

                case 6:
                    System.out.println("Thank you for visiting!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}
