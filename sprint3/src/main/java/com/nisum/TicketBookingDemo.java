package com.nisum;

// TicketBookingSystem handles synchronized booking
class TicketBookingSystem {
    private int availableTickets = 5;

    // Synchronized method to handle booking
    public synchronized void bookTicket(String user, int requestedTickets) {
        if (requestedTickets <= availableTickets) {
            System.out.println(user + " booked " + requestedTickets + " ticket(s).");
            availableTickets -= requestedTickets;
            System.out.println("Tickets left: " + availableTickets);
        } else {
            System.out.println("Sorry " + user + ", not enough tickets. Available: " + availableTickets);
        }
    }
}

// BookingThread tries to book tickets
class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final String userName;
    private final int ticketsToBook;

    public BookingThread(TicketBookingSystem system, String userName, int ticketsToBook) {
        this.system = system;
        this.userName = userName;
        this.ticketsToBook = ticketsToBook;
    }

    public void run() {
        system.bookTicket(userName, ticketsToBook);
    }
}

// Main class
public class TicketBookingDemo {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();

        BookingThread user1 = new BookingThread(system, "Rohit", 2);
        BookingThread user2 = new BookingThread(system, "Rohan", 2);
        BookingThread user3 = new BookingThread(system, "KARAN", 2);

        user1.start();
        user2.start();
        user3.start();
    }
}
