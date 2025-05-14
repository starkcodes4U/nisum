package DAY_3;

import java.util.*;

class Event {
    String title;
    String time;
    String description;

    Event(String title, String time, String description) {
        this.title = title;
        this.time = time;
        this.description = description;
    }

    @Override
    public String toString() {
        return time + " - " + title + ": " + description;
    }
}

public class EventCalendarApp {
    static TreeMap<String, List<Event>> calendar = new TreeMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n1. Add Event\n2. Remove Event\n3. View Events for Date\n4. View All Events\n5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addEvent();
                case 2 -> removeEvent();
                case 3 -> viewEventsByDate();
                case 4 -> viewAllEvents();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    static void addEvent() {
        System.out.print("Enter event date (YYYY-MM-DD): ");
        String date = sc.nextLine();
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter time (e.g., 10:00 AM): ");
        String time = sc.nextLine();
        System.out.print("Enter description: ");
        String description = sc.nextLine();

        Event newEvent = new Event(title, time, description);
        calendar.putIfAbsent(date, new ArrayList<>());
        calendar.get(date).add(newEvent);
        System.out.println("Event added.");
    }

    static void removeEvent() {
        System.out.print("Enter event date (YYYY-MM-DD): ");
        String date = sc.nextLine();
        if (!calendar.containsKey(date)) {
            System.out.println("No events on this date.");
            return;
        }

        List<Event> events = calendar.get(date);
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ". " + events.get(i));
        }

        System.out.print("Enter event number to remove: ");
        int num = sc.nextInt();
        sc.nextLine();

        if (num > 0 && num <= events.size()) {
            events.remove(num - 1);
            if (events.isEmpty()) {
                calendar.remove(date);
            }
            System.out.println("Event removed.");
        } else {
            System.out.println("Invalid event number.");
        }
    }

    static void viewEventsByDate() {
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = sc.nextLine();
        if (!calendar.containsKey(date)) {
            System.out.println("No events found for this date.");
            return;
        }

        System.out.println("Events on " + date + ":");
        for (Event e : calendar.get(date)) {
            System.out.println(e);
        }
    }

    static void viewAllEvents() {
        if (calendar.isEmpty()) {
            System.out.println("No upcoming events.");
            return;
        }

        System.out.println("Upcoming Events:");
        for (Map.Entry<String, List<Event>> entry : calendar.entrySet()) {
            System.out.println("\nDate: " + entry.getKey());
            for (Event e : entry.getValue()) {
                System.out.println(e);
            }
        }
    }
}

